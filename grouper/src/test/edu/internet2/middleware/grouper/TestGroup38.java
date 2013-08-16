/*
  Copyright (C) 2004-2007 University Corporation for Advanced Internet Development, Inc.
  Copyright (C) 2004-2007 The University Of Chicago

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package edu.internet2.middleware.grouper;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;

import edu.internet2.middleware.grouper.exception.InsufficientPrivilegeException;
import edu.internet2.middleware.grouper.registry.RegistryReset;
import edu.internet2.middleware.grouper.util.GrouperUtil;
import edu.internet2.middleware.subject.Subject;

/**
 * @author  blair christensen.
 * @version $Id: TestGroup38.java,v 1.5 2008-09-29 03:38:27 mchyzer Exp $
 * @since   1.1.0
 */
public class TestGroup38 extends TestCase {

  private static final Log LOG = GrouperUtil.getLog(TestGroup38.class);

  public TestGroup38(String name) {
    super(name);
  }

  protected void setUp () {
    LOG.debug("setUp");
    RegistryReset.reset();
  }

  protected void tearDown () {
    LOG.debug("tearDown");
  }

  public void testThrowIPNotGModifyException() {
    LOG.info("testThrowIPNotGModifyException");
    try {
      R         r       = R.populateRegistry(1, 1, 1);
      Group     gA      = r.getGroup("a", "a");
      Subject   subjA   = r.getSubject("a");

      GrouperSession  s = GrouperSession.start(subjA);
      Group           a = GroupFinder.findByName(s, gA.getName());
      try {
        a.setAttribute("description", "new value");
        a.store();
        Assert.fail("FAIL: set description w/out priv");
      }
      catch (InsufficientPrivilegeException eIP) {
        Assert.assertTrue("OK: threw right exception type", true);
      }
      s.stop();

      r.rs.stop();
    }
    catch (Exception e) {
      T.e(e);
    }
  } // public void testThrowIPNotGModifyException()

}
