/*
 * Copyright 2013 http4s.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.http4s

import cats.kernel.laws.discipline.EqTests
import org.http4s.laws.discipline.arbitrary._
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean

class CharsetRangeSuite extends Http4sSuite {
  test("* should match all charsets") {
    forAll { (range: CharsetRange.`*`, cs: Charset) =>
      range.matches(cs)
    }
  }

  test("atomic charset ranges should match their own charsest") {
    forAll(arbitrary[CharsetRange.Atom]) { range =>
      range.matches(range.charset)
    }
  }

  test("atomic charset ranges should not be satisfied by any other charsets") {
    forAll { (range: CharsetRange.Atom, cs: Charset) =>
      range.charset != cs ==> !range.matches(cs)
    }
  }

  checkAll("CharsetRange", EqTests[CharsetRange].eqv)
}
