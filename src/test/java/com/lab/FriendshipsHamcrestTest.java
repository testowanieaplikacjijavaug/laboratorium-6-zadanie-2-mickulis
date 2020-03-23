package com.lab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class FriendshipsHamcrestTest
{
	private Friendships friendships = new Friendships();

	@Test
	public void FriendsAreMutual()
	{
		friendships.makeFriends("a", "b");

		List<Boolean> areFriends = Arrays.asList(friendships.areFriends("a", "b"), friendships.areFriends("b", "a"));
		assertThat(areFriends, not(contains(false)));
	}

	@Test
	public void FriendsAddedInBothLists()
	{
		friendships.makeFriends("a", "b");

		assertThat(friendships.getFriendsList("a"), contains("b"));
		assertThat(friendships.getFriendsList("b"), contains("a"));
	}

	@Test
	public void UnregisteredPersonNotFriendsWithRegistered()
	{
		friendships.makeFriends("a", "b");
		assertThat(friendships.areFriends("UNREGISTERED", "b"), is(false));
	}

	@Test
	public void TwoRegisteredNotFriends()
	{
		friendships.makeFriends("a", "b");
		friendships.makeFriends("c", "d");

		assertThat(friendships.areFriends("c", "b"), is(false));
	}

	@Test
	public void UnregisteredPersonsFriendlistIsEmpty()
	{
		assertThat(friendships.getFriendsList("UNREGISTERED"), empty());
	}

	@Test
	public void NoDuplicateFriendships()
	{
		friendships.makeFriends("a", "b");
		friendships.makeFriends("a", "b");
		friendships.makeFriends("a", "b");
		friendships.makeFriends("a", "c");
		friendships.makeFriends("a", "c");
		List<String> list = friendships.getFriendsList("a");
		Set<String> set = new HashSet<>(list);
		String[] setArray = set.toArray(new String[set.size()]);
		assertThat(friendships.getFriendsList("a"), containsInAnyOrder(setArray));
	}

	@Test
	public void MakeFriendsSpacesAreTrimmed()
	{
		friendships.makeFriends(" a   ", "      b     ");
		assertThat(friendships.getFriendsList("a"), contains("b"));
	}

	@Test
	public void AreFriendsSpacesAreTrimmed()
	{
		friendships.makeFriends("a", "b");
		assertThat(friendships.areFriends("         a ", "  b    "), is(true));
	}

	@Test
	public void GetFriendlistSpacesAreTrimmed()
	{
		friendships.makeFriends("a", "b");
		assertThat(friendships.getFriendsList("          a       "), contains("b"));
	}

	@Test
	public void CorrectFriendlist()
	{
		friendships.makeFriends("a", "b");
		friendships.makeFriends("a", "f");
		friendships.makeFriends("a", "d");
		friendships.makeFriends("a", "g");
		friendships.makeFriends("a", "e");
		friendships.makeFriends("a", "c");
		assertThat(friendships.getFriendsList("a"), containsInAnyOrder("b", "c", "d", "e", "f", "g"));
	}

	@Test
	public void MakeFriendsNullFirstInput()
	{
		try
		{
			friendships.makeFriends(null, " aaa");
			Assertions.fail();
		}
		catch(Exception e)
		{
			assertThat(e, instanceOf(IllegalArgumentException.class));
		}
	}

	@Test
	public void MakeFriendsNullSecondInput()
	{
		try
		{
			friendships.makeFriends(null, " aaa");
			Assertions.fail();
		}
		catch(Exception e)
		{
			assertThat(e, instanceOf(IllegalArgumentException.class));
		}
	}

	@Test
	public void MakeFriendsEmptyFirstInput()
	{
		try
		{
			friendships.makeFriends("", " aaa");
			Assertions.fail();
		}
		catch(Exception e)
		{
			assertThat(e, instanceOf(IllegalArgumentException.class));
		}
	}

	@Test
	public void MakeFriendsEmptySecondInput()
	{
		try
		{
			friendships.makeFriends(" aaa", "");
			Assertions.fail();
		}
		catch(Exception e)
		{
			assertThat(e, instanceOf(IllegalArgumentException.class));
		}
	}

	@Test
	public void MakeFriendsWithOneself()
	{
		try
		{
			friendships.makeFriends("a", "a");
			Assertions.fail();
		}
		catch(Exception e)
		{
			assertThat(e, instanceOf(IllegalArgumentException.class));
		}
	}

	@Test
	public void GetFriendsNullInput()
	{
		try
		{
			friendships.getFriendsList(null);
			Assertions.fail();
		}
		catch(Exception e)
		{
			assertThat(e, instanceOf(NullPointerException.class));
		}
	}

	@Test
	public void AreFriendsNullFirstInput()
	{
		try
		{
			friendships.areFriends(null, "asd");
			Assertions.fail();
		}
		catch(Exception e)
		{
			assertThat(e, instanceOf(NullPointerException.class));
		}
	}

	@Test
	public void AreFriendsNullSecondInput()
	{
		try
		{
			friendships.areFriends("asd", null);
			Assertions.fail();
		}
		catch(Exception e)
		{
			assertThat(e, instanceOf(NullPointerException.class));
		}
	}
}
