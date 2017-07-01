package com.example

import org.glassfish.jersey.server.ResourceConfig

class SampleConfig extends ResourceConfig {

  packages(this.getClass.getPackage.getName)
}
