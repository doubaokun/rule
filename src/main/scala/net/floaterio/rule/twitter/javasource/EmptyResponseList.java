package net.floaterio.rule.twitter.javasource;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/25
 * Time: 0:29
 * To change this template use File | Settings | File Templates.
 */
public class EmptyResponseList<T> implements ResponseList<T>{

    class EmptyRateLimitStatus implements RateLimitStatus {
        @Override
        public int getRemainingHits() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getHourlyLimit() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getResetTimeInSeconds() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getSecondsUntilReset() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Date getResetTime() {
            return new Date();  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    @Override
    public RateLimitStatus getRateLimitStatus() {
        return new EmptyRateLimitStatus();
    }

    @Override
    public RateLimitStatus getFeatureSpecificRateLimitStatus() {
        return new EmptyRateLimitStatus();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEmpty() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean contains(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.empty();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object[] toArray() {
        return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean add(T t) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean remove(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addAll(Collection<? extends T> ts) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> ts) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T get(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T set(int i, T t) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void add(int i, T t) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T remove(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int indexOf(Object o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<T> subList(int i, int i1) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getAccessLevel() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
