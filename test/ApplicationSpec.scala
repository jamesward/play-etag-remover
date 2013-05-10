import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends Specification {

  "Assets" should {
    "contain an etag" in new WithApplication {
      val result = controllers.Assets.at("/public", "images/favicon.png")(FakeRequest())
      status(result) must equalTo(OK)
      header(ETAG, result) must beSome[String]
    }
  }

  "AssetsSansEtag" should {
    "not contain an etag" in new WithApplication {
      val result = controllers.AssetsSansEtag.at("/public", "images/favicon.png")(FakeRequest())
      status(result) must equalTo(OK)
      header(ETAG, result) must beNone
    }
  }

}