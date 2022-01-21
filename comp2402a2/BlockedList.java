package comp2402a2;

import java.util.AbstractList;
import java.util.Collection;
import java.util.*;
import java.util.Stack;

/**
 * @author morin
 *
 * @param <T> the type of objects stored in the List
 */
 public class BlockedList<T> extends AbstractList<T> {
 	/**
 	 * The class of elements stored in this queue
 	 */
 	protected Factory<T> f;
	/**
	 * The block size
	 */
	protected int b;
	/**
	 * Stacks to hold the elements
	 * Split into two different ones
	 */
	protected List<T> front;
	protected List<T> back;

	//Resizes and balances the stacks
	void balance() {
			int n = size();
			if (3*front.size() < back.size()) {
					int s = n/2 - front.size();
					List<T> l1 = new Stack();
					List<T> l2 = new Stack();
					l1.addAll(back.subList(0,s));
					Collections.reverse(l1);
					l1.addAll(front);
					l2.addAll(back.subList(s, back.size()));
					front = l1;
					back = l2;
			} else if (3*back.size() < front.size()) {
					int s = front.size() - n/2;
					List<T> l1 = new Stack();
					List<T> l2 = new Stack();
					l1.addAll(front.subList(s, front.size()));
					l2.addAll(front.subList(0, s));
					Collections.reverse(l2);
					l2.addAll(back);
					front = l1;
					back = l2;
			}
	}

 	/**
 	 * Constructor
 	 */
 	public BlockedList(Class<T> t, int b) {
 		f = new Factory<T>(t);
 		front = new Stack();	//Initializes the stackss
		back = new Stack();
		this.b = b;
 	}

 	public int size() {
    return front.size() + back.size();	//Adds both stacks to get the proper size
 	}

 	public T get(int i) {
		if (i < front.size()) {	//Checks which side the index is on and grabs it through index easily through a stack
			return front.get(front.size()-i-1);
		} else {
			return back.get(i-front.size());
		}
 	}

 	public T set(int i, T x) {
		if (i < front.size()) {	//Same as get function however it modifies it this time
				return front.set(front.size()-i-1, x);

		} else {
				return back.set(i-front.size(), x);
		}
 	}

 	public void add(int i, T x) {

		if (i < front.size()) {	//Easily adds to the back or front and checks where it goes again. Adds easily due to it being a stack
				front.add(front.size()-i, x);
		} else {
				back.add(i-front.size(), x);
		}
		balance(); //Rebalances the stacks as the size may have changed and some indexes may need to be moved
 	}

 	public T remove(int i) {

		T x;
		if (i < front.size()) {	//Easily removes to the back or front and checks where it goes again. Removes easily due to stack functions.
				x = front.remove(front.size()-i-1);
		} else {
				x = back.remove(i-front.size());
		}
		balance();	//Rebalances the stacks as the size has changed and some indexes may need to mvoe to the lower/upper end

		return x;

 	}



 }
