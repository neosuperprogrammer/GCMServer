package flowgrammer.DAO.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import flowgrammer.DAO.GCMDao;
import flowgrammer.model.PushId;
import flowgrammer.model.Team;

public class GCMDaoTest {
	public static final String regId = "APA91bESXM0WH5qo3P22aInKi7WiR1FH_TzHlURw4oCAKfXOuETTPl-JqBN5PbsFjPSql2_34eOodFeDJ5P5pAIfhQAJh-joA1Gg_rvxz-_GyebF5SllkcjWMRIPAGFHPclXK_y8wzqH";

	@Test
	public void test() {
		List<Team> teamList = GCMDao.getTeam();
		assertTrue(teamList.size() == 1);
	}

	@Test
	public void test_count_ofPushId_isOne() {
		List<PushId> pushIds = GCMDao.getPushId();
		assertTrue(pushIds.size() == 1);
	}
	
	@Test
	public void test_Idof_PushId()  {
		List<PushId> pushIds = GCMDao.getPushId();
		PushId pushId = pushIds.get(0);
		assertTrue(pushId.pushId().equals(regId));
	}
}
