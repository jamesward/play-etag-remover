package controllers

import play.api.mvc._

object AssetsSansEtag extends Controller {

  def at(path: String, file: String) = Action { request =>
    val result = Assets.at(path, file)(request)
  
    result match {
      case r: SimpleResult[_] => r.copy(header = r.header.copy(headers = r.header.headers - ETAG))(r.writeable)
      case _ => result
    }
  }

}