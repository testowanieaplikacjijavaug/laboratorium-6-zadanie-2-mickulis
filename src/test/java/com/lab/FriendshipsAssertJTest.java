package com.lab;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class FriendshipsAssertJTest
{
	private Friendships friendships = new Friendships();

	@Test
	public void FriendsAreMutual()
	{
		friendships.makeFriends("a", "b");

		List<Boolean> areFriends = Arrays.asList(friendships.areFriends("a", "b"), friendships.areFriends("b", "a"));
		assertThat(areFriends).doesNotContain(false);
	}

	@Test
	public void FriendsAddedInBothLists()
	{
		friendships.makeFriends("a", "b");

		assertThat(friendships.getFriendsList("a")).contains("b");
		assertThat(friendships.getFriendsList("b")).contains("a");
	}

	@Test
	public void UnregisteredPersonNotFriendsWithRegistered()
	{
		friendships.makeFriends("a", "b");
		assertThat(friendships.areFriends("UNREGISTERED", "b")).isFalse();
	}

	@Test
	public void TwoRegisteredNotFriends()
	{
		friendships.makeFriends("a", "b");
		friendships.makeFriends("c", "d");

		assertThat(friendships.areFriends("c", "b")).isFalse();
	}

	@Test
	public void UnregisteredPersonsFriendlistIsEmpty()
	{
		assertThat(friendships.getFriendsList("UNREGISTERED")).isEmpty();
	}

	@Test
	public void NoDuplicateFriendships()
	{
		friendships.makeFriends("a", "b");
		friendships.makeFriends("a", "b");
		friendships.makeFriends("a", "b");
		friendships.makeFriends("a", "c");
		friendships.makeFriends("a", "c");
		assertThat(friendships.getFriendsList("a")).doesNotHaveDuplicates();
	}

	@Test
	public void MakeFriendsSpacesAreTrimmed()
	{
		friendships.makeFriends(" a   ", "      b     ");
		assertThat(friendships.getFriendsList("a")).containsExactly("b");
	}

	@Test
	public void AreFriendsSpacesAreTrimmed()
	{
		friendships.makeFriends("a", "b");
		assertThat(friendships.areFriends("         a ", "  b    ")).isTrue();
	}

	@Test
	public void GetFriendlistSpacesAreTrimmed()
	{
		friendships.makeFriends("a", "b");
		assertThat(friendships.getFriendsList("          a       ")).containsExactly("b");
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
		assertThat(friendships.getFriendsList("a")).containsExactlyInAnyOrder("b", "c", "d", "e", "f", "g");
	}

	@Test
	public void MakeFriendsNullFirstInput()
	{
		Throwable thrown = catchThrowable(() -> friendships.makeFriends(null, " aaa"));
		assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void MakeFriendsNullSecondInput()
	{
		Throwable thrown = catchThrowable(() -> friendships.makeFriends(" aaa", null));
		assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void MakeFriendsEmptyFirstInput()
	{
		Throwable thrown = catchThrowable(() -> friendships.makeFriends("", " aaa"));
		assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void MakeFriendsEmptySecondInput()
	{
		Throwable thrown = catchThrowable(() -> friendships.makeFriends(" aaa", ""));
		assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void MakeFriendsWithOneself()
	{
		Throwable thrown = catchThrowable(() -> friendships.makeFriends("a", "a"));
		assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void GetFriendsNullInput()
	{
		Throwable thrown = catchThrowable(() -> friendships.getFriendsList(null));
		assertThat(thrown).isInstanceOf(NullPointerException.class);
	}

	@Test
	public void AreFriendsNullFirstInput()
	{
		Throwable thrown = catchThrowable(() -> friendships.areFriends(null, "asd"));
		assertThat(thrown).isInstanceOf(NullPointerException.class);
	}

	@Test
	public void AreFriendsNullSecondInput()
	{
		Throwable thrown = catchThrowable(() -> friendships.areFriends("asd", null));
		assertThat(thrown).isInstanceOf(NullPointerException.class);
	}
}
