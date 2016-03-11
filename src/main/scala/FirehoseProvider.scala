import com.amazonaws.regions.{Region, Regions}

class FirehoseProvider {

  def createFireHoseClient(): com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsyncClient = {
    val client = new com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsyncClient

    val currentRegion = Option(Regions.getCurrentRegion) map { r => Regions.getCurrentRegion } getOrElse Region.getRegion(Regions.US_EAST_1)

    client.withRegion(currentRegion)
  }
}
