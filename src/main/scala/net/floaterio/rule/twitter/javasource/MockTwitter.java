package net.floaterio.rule.twitter.javasource;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/21
 * Time: 2:35
 * To change this template use File | Settings | File Templates.
 */

public class MockTwitter implements Twitter{

    @Override
    public User verifyCredentials() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RateLimitStatus getRateLimitStatus() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User updateProfileColors(String profileBackgroundColor, String profileTextColor, String profileLinkColor, String profileSidebarFillColor, String profileSidebarBorderColor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User updateProfileImage(File image) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User updateProfileImage(InputStream image) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User updateProfileBackgroundImage(File image, boolean tile) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User updateProfileBackgroundImage(InputStream image, boolean tile) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User updateProfile(String name, String url, String location, String description) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccountTotals getAccountTotals() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccountSettings getAccountSettings() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccountSettings updateAccountSettings(Integer trendLocationWoeid, Boolean sleepTimeEnabled, String startSleepTime, String endSleepTime, String timeZone, String lang) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User createBlock(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User createBlock(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User destroyBlock(String screen_name) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User destroyBlock(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean existsBlock(String screenName) throws TwitterException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean existsBlock(long userId) throws TwitterException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> getBlockingUsers() throws TwitterException {
        return new EmptyResponseList<User>();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> getBlockingUsers(int page) throws TwitterException {
        return new EmptyResponseList<User>();    //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getBlockingUsersIDs() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<DirectMessage> getDirectMessages() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<DirectMessage> getDirectMessages(Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<DirectMessage> getSentDirectMessages() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<DirectMessage> getSentDirectMessages(Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DirectMessage sendDirectMessage(String screenName, String text) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DirectMessage sendDirectMessage(long userId, String text) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DirectMessage destroyDirectMessage(long id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DirectMessage showDirectMessage(long id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getFavorites() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getFavorites(int page) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getFavorites(String id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getFavorites(String id, int page) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getFavorites(Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getFavorites(String id, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Status createFavorite(long id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Status destroyFavorite(long id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getFriendsIDs(long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getFriendsIDs(long userId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getFriendsIDs(String screenName, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getFollowersIDs(long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getFollowersIDs(long userId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getFollowersIDs(String screenName, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User createFriendship(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User createFriendship(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User createFriendship(String screenName, boolean follow) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User createFriendship(long userId, boolean follow) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User destroyFriendship(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User destroyFriendship(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean existsFriendship(String userA, String userB) throws TwitterException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Relationship showFriendship(String sourceScreenName, String targetScreenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Relationship showFriendship(long sourceId, long targetId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getIncomingFriendships(long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getOutgoingFriendships(long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Friendship> lookupFriendships(String[] screenNames) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Friendship> lookupFriendships(long[] ids) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Relationship updateFriendship(String screenName, boolean enableDeviceNotification, boolean retweets) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Relationship updateFriendship(long userId, boolean enableDeviceNotification, boolean retweets) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getNoRetweetIds() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Place> searchPlaces(GeoQuery query) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SimilarPlaces getSimilarPlaces(GeoLocation location, String name, String containedWithin, String streetAddress) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Place> reverseGeoCode(GeoQuery query) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Place getGeoDetails(String id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Place createPlace(String name, String containedWithin, String token, GeoLocation location, String streetAddress) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean test() throws TwitterException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TwitterAPIConfiguration getAPIConfiguration() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Language> getLanguages() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTermsOfService() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPrivacyPolicy() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getUserListMembers(String listOwnerScreenName, int listId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getUserListMembers(long listOwnerId, int listId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getUserListMembers(int listId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList addUserListMember(int listId, long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList addUserListMembers(int listId, long[] userIds) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList addUserListMembers(int listId, String[] screenNames) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList deleteUserListMember(int listId, long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User checkUserListMembership(String listOwnerScreenName, int listId, long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User showUserListMembership(int listId, long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList createUserList(String listName, boolean isPublicList, String description) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList updateUserList(int listId, String newListName, boolean isPublicList, String newDescription) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<UserList> getUserLists(String listOwnerScreenName, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<UserList> getUserLists(long listOwnerUserId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList showUserList(String listOwnerScreenName, int id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList showUserList(int listId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList destroyUserList(int listId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserListStatuses(String listOwnerScreenName, int id, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserListStatuses(long listOwnerId, int id, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserListStatuses(int listId, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(long listMemberId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(String listMemberScreenName, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(String listMemberScreenName, long cursor, boolean filterToOwnedLists) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<UserList> getUserListMemberships(long listMemberId, long cursor, boolean filterToOwnedLists) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<UserList> getUserListSubscriptions(String listOwnerScreenName, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<UserList> getAllUserLists(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<UserList> getAllUserLists(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(String listOwnerScreenName, int listId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getUserListSubscribers(int listId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList subscribeUserList(String listOwnerScreenName, int listId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList createUserListSubscription(int listId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList unsubscribeUserList(String listOwnerScreenName, int listId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserList destroyUserListSubscription(int listId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User checkUserListSubscription(String listOwnerScreenName, int listId, long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User showUserListSubscription(int listId, long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Location> getAvailableTrends() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Location> getAvailableTrends(GeoLocation location) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Trends getLocationTrends(int woeid) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public RelatedResults getRelatedResults(long statusId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User enableNotification(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User enableNotification(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User disableNotification(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User disableNotification(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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

    @Override
    public ResponseList<SavedSearch> getSavedSearches() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedSearch showSavedSearch(int id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedSearch createSavedSearch(String query) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SavedSearch destroySavedSearch(int id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QueryResult search(Query query) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User reportSpam(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User reportSpam(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Status showStatus(long id) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Status updateStatus(String status) throws TwitterException {
        System.out.println("update status" + status);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Status updateStatus(StatusUpdate latestStatus) throws TwitterException {
        System.out.println("update status status="+latestStatus.getStatus()+
                " reply=" + latestStatus.getInReplyToStatusId());
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Status destroyStatus(long statusId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Status retweetStatus(long statusId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweets(long statusId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> getRetweetedBy(long statusId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> getRetweetedBy(long statusId, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getRetweetedByIDs(long statusId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IDs getRetweetedByIDs(long statusId, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getPublicTimeline() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getHomeTimeline() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getHomeTimeline(Paging paging) throws TwitterException {
        return new EmptyResponseList<Status>();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getFriendsTimeline() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getFriendsTimeline(Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserTimeline(String screenName, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserTimeline(long userId, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserTimeline(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserTimeline(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserTimeline() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getUserTimeline(Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getMentions() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getMentions(Paging paging) throws TwitterException {
        return new EmptyResponseList<Status>();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetedByMe() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetedByMe(Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetedToMe() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetedToMe(Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetsOfMe() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetsOfMe(Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetedToUser(String screenName, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetedToUser(long userId, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetedByUser(String screenName, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Status> getRetweetedByUser(long userId, Paging paging) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Trends> getDailyTrends() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Trends> getDailyTrends(Date date, boolean excludeHashTags) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Trends> getWeeklyTrends() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Trends> getWeeklyTrends(Date date, boolean excludeHashTags) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
    public User showUser(String screenName) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User showUser(long userId) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> lookupUsers(String[] screenNames) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> lookupUsers(long[] ids) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> searchUsers(String query, int page) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<Category> getSuggestedUserCategories() throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> getUserSuggestions(String categorySlug) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseList<User> getMemberSuggestions(String categorySlug) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ProfileImage getProfileImage(String screenName, ProfileImage.ImageSize size) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getFriendsStatuses(long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getFriendsStatuses(String screenName, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getFriendsStatuses(long userId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getFollowersStatuses(long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getFollowersStatuses(String screenName, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PagableResponseList<User> getFollowersStatuses(long userId, long cursor) throws TwitterException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
