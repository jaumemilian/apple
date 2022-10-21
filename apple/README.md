# Apple Helm Chart

## Installing

To install the chart with the release name `my-release`:

```
helm install --name my-release asg/apple
```

To uninstall/delete the `my-release` deployment:

```
helm delete my-release
```


## Customizing

You can customize a deployment with the variables. 
The following tables lists the configurable parameters of the apple chart and their default values.

|         Parameter                    |                Description                         |                            Default                        |
|--------------------------------------|----------------------------------------------------|---------------------------------------------------------- |
| `image.repository`                   | Specify image docker registry                      | `{{docker-registry.url}}`                                 |
| `image.name`                         | The name of the image                              | `apple-boot`                                          |
| `image.tag`                          | Apple Image tag                                | `latest`                                                  |
| `nameOverride`                       | The name of the service to publish with            | `apple`                                               |
| `fullnameOverride`                   | The full name of the service to publish with       | `RELEASE-apple`                                       |
| `image.pullPolicy`                   | Apple Image pull policy                        | `Always`                                                  |
| `service.type`                       | Kubernetes Service type                            | `NodePort`                                                |
| `service.port`                       | Apple port                                     | `8500`                                                    |
| `service.annotations`                | Annotations for Apple service                 | `{}`                                                      |                                                 
| `resources`                          | CPU/Memory resource requests/limits                | `{}`                                                      |
| `nodeSelector`                       | Node labels for pod assignment                     | `{}`                                                      |
| `tolerations`                        | Toleration labels for pod assignment               | `[]`                                                      |
| `affinity`                           | Node affinity                                      | `{}`                                                      |