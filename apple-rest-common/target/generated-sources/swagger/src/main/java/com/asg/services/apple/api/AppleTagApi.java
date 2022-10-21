package com.asg.services.apple.api;

import com.asg.services.apple.dto.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.servers.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.asg.services.apple.dto.Apple;
import com.asg.services.apple.dto.AppleCreateRequest;
import com.asg.services.apple.dto.AppleList;
import com.asg.services.apple.dto.AppleUpdateRequest;

import java.util.Map;
import java.util.List;

import java.io.InputStream;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import org.glassfish.jersey.media.multipart.*;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import javax.validation.constraints.*;

@Path("/apple/apples")




@OpenAPIDefinition(
    info = @Info(
                title = "Apple",
                version = "1.0.0",
                description = "Apple swagger definiton"
    ),
    servers = @Server(url = "/rest")
)
public interface AppleTagApi  {
    @POST
    
    @Consumes({ "application/vnd.asg-services-apple-create-request.v1+json" })
    @Produces({ "application/vnd.asg-services-apple.v1+json" })
    @Operation(summary = "create a Apple", description = "create a Apple", tags={ "AppleTag" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created a Apple", content = @Content(schema = @Schema(implementation = Apple.class))) })
   public Response createApple(@Parameter(description = "" ) AppleCreateRequest body

);
    @DELETE
    @Path("/{appleId}")
    
    
    @Operation(summary = "Deletes a Apple", description = "Deletes a Apple", tags={ "AppleTag" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "The Apple was deleted") })
   public Response deleteApple(@Parameter(description = "apple Id",required=true) @PathParam("appleId") String appleId
);
    @GET
    
    
    @Produces({ "application/vnd.asg-services-apple-list.v1+json" })
    @Operation(summary = "return all Apples", description = "return all Apples", tags={ "AppleTag" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "List of Apples", content = @Content(schema = @Schema(implementation = AppleList.class))) })
   public Response getAllApples();
    @GET
    @Path("/{appleId}")
    
    @Produces({ "application/vnd.asg-services-apple.v1+json" })
    @Operation(summary = "return a Apple", description = "return a Apple", tags={ "AppleTag" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The Apple", content = @Content(schema = @Schema(implementation = Apple.class))) })
   public Response getApple(@Parameter(description = "apple Id",required=true) @PathParam("appleId") String appleId
);
    @PUT
    @Path("/{appleId}")
    @Consumes({ "application/vnd.asg-services-apple-update-request.v1+json" })
    @Produces({ "application/vnd.asg-services-apple.v1+json" })
    @Operation(summary = "Update a Apple", description = "Update a Apple", tags={ "AppleTag" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The updated Apple", content = @Content(schema = @Schema(implementation = Apple.class))) })
   public Response updateApple(@Parameter(description = "apple Id",required=true) @PathParam("appleId") String appleId
, @Parameter(description = "" ) AppleUpdateRequest body

);
}
