#!/usr/bin/env groovy
pipeline{
    agent any

    stages {
        stage('Deploy') {
            steps {
                //The Jenkins Declarative Pipeline does not provide functionality to deploy to a private
                //Docker registry. In order to deploy to the HDAP Docker registry we must write a custom Groovy
                //script using the Jenkins Scripting Pipeline. This is done by placing Groovy code with in a "script"
                //element. The script below registers the HDAP Docker registry with the Docker instance used by
                //the Jenkins Pipeline, builds a Docker image of the project, and pushes it to the registry.
                script{
                    docker.withRegistry('https://gt-build.hdap.gatech.edu'){
                        //Build and push the database image
                        def jwks_server = docker.build("jwks-server:1.0", "-f ./jwks_server/Dockerfile ./jwks_server")
                        jwks_server.push('latest')
                    }
                }
            }
        }

        stage('Notify') {
            steps {
                script{
                    rancher confirm: true, credentialId: 'rancher-server', endpoint: 'https://rancher.hdap.gatech.edu/v2-beta', environmentId: '1a7', environments: '', image: 'build.hdap.gatech.edu/jwks-server:latest', ports: '8080:80', service: 'fbo/jwks-server', timeout: 50
                }
            }
        }
    }
}
