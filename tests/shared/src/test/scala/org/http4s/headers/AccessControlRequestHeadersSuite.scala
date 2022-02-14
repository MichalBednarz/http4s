package org.http4s.headers

import org.http4s.laws.discipline.arbitrary._

class AccessControlRequestHeadersSuite extends HeaderLaws {
  checkAll("Access-Control-Request-Headers", headerLaws[`Access-Control-Request-Headers`])
}
