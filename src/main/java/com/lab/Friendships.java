package com.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Friendships {
	Map<String, List<String>> friendships = new HashMap<>();

	//Dodanie przyjaciół - wykorzystuje funkcje addFriend!
	public void makeFriends(String person1, String person2)
	{
		if(person1 == null || person2 == null)
			throw new IllegalArgumentException("Person's name cannot be null");

		person1 = person1.trim();
		person2 = person2.trim();

		if(person1.isEmpty() || person2.isEmpty())
			throw new IllegalArgumentException("Person's name cannot be empty");

		if(person1.equalsIgnoreCase(person2))
			throw new IllegalArgumentException("Person cannot be friends with oneself");

		addFriend(person1, person2);
		addFriend(person2, person1);
	}

	//Pobranie listy przyjaciol
	public List<String> getFriendsList(String person)
	{
		person = person.trim();
		if(friendships.containsKey(person))
			return friendships.get(person);
		else return new ArrayList<>();
	}

	//Sprawdzenie czy przyjaciele
	public boolean areFriends(String person1, String person2)
	{
		person1 = person1.trim();
		person2 = person2.trim();
		return friendships.containsKey(person1) && friendships.get(person1).contains(person2);
	}

	//Dodanie do listy przyjaciol do danej osoby
	private void addFriend(String person, String friend)
	{
		if(!friendships.containsKey(person))
			friendships.put(person, new ArrayList<>());

		List<String> personsFriendlist = friendships.get(person);
		if(!personsFriendlist.contains(friend))
			personsFriendlist.add(friend);
	}
}
