// We are still using inline yaml file as it throws  "Required context class hudson.FilePath is missing"
// error when we try to use readFile function.
// This issue is fixed for declarative Jenkinsfile but not for scripted Jenkinsfile
podTemplate(
  cloud: 'usyp5docmob06v',
  name: 'maven',
  yaml: """
apiVersion: v1
kind: Pod
metadata:
  labels:
    os: linux
    maven: 3.5.4
spec:
  containers:
  - name: maven
    image: maven:3.5.4-jdk-8
    command:
    - cat
    tty: true
    securityContext:
      privileged: true
    env:
      - name: artifactory_url
        valueFrom:
          secretKeyRef:
            name: mobius
            key: rtserverurl
      - name: artifactory_password
        valueFrom:
          secretKeyRef:
            name: mobius
            key: rtserverapikey
      - name: artifactory_user
        valueFrom:
          secretKeyRef:
            name: mobius
            key: rtserverusername
      - name: WHITESOURCE_KEY
        valueFrom:
          secretKeyRef:
            name: mobius
            key: whitesourceapikey

"""
) {
    node(POD_LABEL) {
        try {

            /* --- DO NOT CHANGE THESE VARIABLES, IT IS MANAGED BY A JENKINS RELEASE JOB --- */
           def name = 'apple'
           def pname = ' '
           def releaseBranch = 'master'
           def version = '1.0.0-SNAPSHOT'
            /* ----------------------------------------------------------------------------- */

            checkout scm

            stage("Setup") {
                container('maven') {
                    sh """
                        export

                        mkdir /root/.m2
                        cp settings.xml /root/.m2/settings.xml

                        apt-get update && apt-get install -y apt-transport-https curl openssl gawk ca-certificates libgpgme-dev libassuan-dev libdevmapper-dev build-essential go-md2man pkgconf
                        curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
                        echo "deb http://apt.kubernetes.io/ kubernetes-xenial main " >> /etc/apt/sources.list.d/kubernetes.list
                        apt-get update
                        apt-get install -y kubectl

                        curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3
                        chmod 700 get_helm.sh
                        ./get_helm.sh

                        curl -fSL "https://github.com/genuinetools/img/releases/download/v0.5.6/img-linux-amd64" -o "/usr/local/bin/img" && chmod a+x "/usr/local/bin/img"

                        echo | openssl s_client -showcerts -servername wal-artifactory.rocketsoftware.com -connect wal-artifactory.rocketsoftware.com:443 2>/dev/null | awk '/-----BEGIN CERTIFICATE-----/, /-----END CERTIFICATE-----/' >> /usr/local/share/ca-certificates/ca-certificates.crt
                        update-ca-certificates

                        # Currently, JFrog Artifactory doesn't support monolithic docker upload for the push.
                        # So, img push always fails. Workaround for this is to use skopeo to push the image
                        # Ref: https://www.jfrog.com/jira/browse/RTFACT-19344
                        # https://github.com/genuinetools/img/issues/128
                        # https://github.com/containerd/containerd/issues/3533

                        wget https://dl.google.com/go/go1.13.3.linux-amd64.tar.gz
                        tar -xvf go1.13.3.linux-amd64.tar.gz
                        mv go /usr/local
                        export GOROOT=/usr/local/go
                        export GOPATH=/tmp
                        export PATH=\$GOPATH/bin:\$GOROOT/bin:\$PATH
                        git clone -b 'v0.1.40' --single-branch https://github.com/containers/skopeo \$GOPATH/src/github.com/containers/skopeo
                        cd \$GOPATH/src/github.com/containers/skopeo && make binary-local
                        make install
                    """
                }
            }

            stage('Env') {
                container('maven') {
                    // TODO: replace with environment variables in upstream settings.xml
                    sh """
                      sed -i -e \"s;{{artifactory.url}};\$artifactory_url;g\" /root/.m2/settings.xml
                      sed -i -e \"s;{{sonar.host.url}};\$sonar_url;g\" /root/.m2/settings.xml
                      sed -i -e \"s/{{user}}/\$artifactory_user/g\" /root/.m2/settings.xml
                      sed -i -e \"s/{{password}}/\$artifactory_password/g\" /root/.m2/settings.xml
                      ls ~
                    """
                }
            }

            stage('Build') {
                container('maven') {
                    sh 'mvn clean install -B -U -P quality -DskipTests'
                }
            }


      /*
      Push to Sonarqube
      */
/*
            stage('Sonar') {
                 container('maven') {
                     sh 'mvn test -P quality -B sonar:sonar -Dsonar.projectKey=$SONAR_PROJECT_KEY -Dsonar.projectVersion=1.0.0-SNAPSHOT -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_LOGIN'
                 }
             }
*/


             stage('Veracode-SCA') {
                 container('maven') {
                    sh """
                       export SRCCLR_API_TOKEN=$VERACODE_TOKEN
                       curl -sSL https://download.sourceclear.com/ci.sh | sh
                    """
                 }
             }

             stage('Deploy Jars') {
                container('maven') {
                    sh """
                      mvn -pl '!$pname-war,!$pname-events' jar:jar deploy:deploy
                      mvn -pl $pname-events deploy
                      mvn -f $pname-war/pom.xml -B war:war spring-boot:repackage deploy:deploy
                    """
                }
             }

             stage('Deploy Docker') {
                container('maven') {
                     sh """
                       img login -u \$artifactory_user -p \$artifactory_password \$DOCKER_REGISTRY_URL
                       img build -t ${pname}:${version} -f ./boot.dockerfile .
                       img tag ${pname}:${version} \$DOCKER_REGISTRY_HOST/${pname}:${version}
                       img save -o "${pname}_${version}.tar" \$DOCKER_REGISTRY_HOST/${pname}:${version}
                       skopeo copy docker-archive:${pname}_${version}.tar docker://\$DOCKER_REGISTRY_HOST/${pname}:${version}
                       img logout \$DOCKER_REGISTRY_URL
                     """
                 }
             }

                 stage('Helm') {
                    container('maven') {

                     sh """
                         sed -i -e "s;{{dockerRegistry.url}};\$DOCKER_REGISTRY_HOST;g" $name/values.yaml
                         helm repo add asg \$HELM_URL --username \$artifactory_user --password \$artifactory_password
                         helm dependency update $name
                         helm package $name
                         curl -u \$artifactory_user:\$artifactory_password -X PUT -T ${name}-${version}.tgz \$HELM_LOCAL_URL/
                     """
                    }
                 }

        } catch (e) {
            currentBuild.result = 'FAILED'
            throw e
        } finally {
            def current = currentBuild.result ?: 'SUCCESS'
            def prev = currentBuild.previousBuild?.result ?: 'SUCCESS'
            if (current != prev) {
                 emailext subject: current + ' $DEFAULT_SUBJECT',
                         from: 'jenkins@asg.com',
                         to: "${env.MAIL_RECIPIENT_LIST}" ?: '',
                         recipientProviders: [culprits(), requestor()],
                         body: current + ' $DEFAULT_CONTENT',
                         attachLog: true,
                         compressLog: true
                echo "====++++ ++++===="
            }
             cleanWs()
        }
    }
}

