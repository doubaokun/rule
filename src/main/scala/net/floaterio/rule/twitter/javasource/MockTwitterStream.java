package net.floaterio.rule.twitter.javasource;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/21
 * Time: 2:36
 * To change this template use File | Settings | File Templates.
 */

public class MockTwitterStream implements TwitterStream{
    @Override
    public void addConnectionLifeCycleListener(ConnectionLifeCycleListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addListener(UserStreamListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addListener(StatusListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addListener(SiteStreamsListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void firehose(int count) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StatusStream getFirehoseStream(int count) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void links(int count) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StatusStream getLinksStream(int count) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void retweet() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StatusStream getRetweetStream() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sample() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StatusStream getSampleStream() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void user() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void user(String[] track) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserStream getUserStream() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserStream getUserStream(String[] track) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void site(boolean withFollowings, long[] follow) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void filter(FilterQuery query) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StatusStream getFilterStream(FilterQuery query) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void cleanUp() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getScreenName() throws TwitterException, IllegalStateException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getId() throws TwitterException, IllegalStateException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addRateLimitStatusListener(RateLimitStatusListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Authorization getAuthorization() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Configuration getConfiguration() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void shutdown() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setOAuthConsumer(String consumerKey, String consumerSecret) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RequestToken getOAuthRequestToken() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RequestToken getOAuthRequestToken(String callbackURL) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RequestToken getOAuthRequestToken(String callbackURL, String xAuthAccessType) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccessToken getOAuthAccessToken() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccessToken getOAuthAccessToken(String oauthVerifier) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccessToken getOAuthAccessToken(RequestToken requestToken) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccessToken getOAuthAccessToken(RequestToken requestToken, String oauthVerifier) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccessToken getOAuthAccessToken(String screenName, String password) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setOAuthAccessToken(AccessToken accessToken) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
