package com.wildex999.tickdynamic.listinject;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EntityListIterator implements ListIterator<EntityObject> {

	private ListManager list;
	private int currentAge; //Used to verify if iterator is still valid(Concurrent modification)
	
	private EntityGroup currentGroup;
	private EntityObject currentObject;
	private Iterator<EntityGroup> groupIterator;
	private List<EntityObject> entityList;
	private int currentIndex;
	private int globalIndex;
	
	public EntityListIterator(ListManager list) {
		this.list = list;
		this.currentAge = list.getAge();
		this.groupIterator = list.getGroupIterator();
		this.currentIndex = 0;
		this.globalIndex = 0;
	}
	
	@Override
	public void add(EntityObject entityObject) {
		//Add at current location
	}

	@Override
	public boolean hasNext() {
		if(currentAge != list.age)
			throw new ConcurrentModificationException("List modified before going to next entry");
		
		while(entityList == null || currentIndex >= entityList.size()) {
			currentIndex = 0;
			entityList = null;
			
			if(!groupIterator.hasNext())
				return false;
			
			currentGroup = groupIterator.next();
			if(currentGroup == null)
				return false;
			
			entityList = currentGroup.entities;
		}
		
		return true;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EntityObject next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EntityObject previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(EntityObject arg0) {
		// TODO Auto-generated method stub
		
	}

}
