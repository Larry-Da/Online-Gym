```java
// for customer
public class PersonalController {
  	Backend backend;
  	String cusId;
  	public PersonalController(String cusId, Backend backend) {
      	this.cusId = cusId;
      	this.backend = backend;
    }
    //获取用户预订的live session
  {
    "request": "getSessionIdsByCusId",
    "payload":{
      "cusId": "Cs13"
    }
  }
  {
    "status":"success"/"failure",
    "payload":{
      "sessionIds":["s001","s002"]
    }
  }
  {
    "status":"success"/"failure",
    "payload":{
      "sessionIds":"s001",
      "url":"usr/local/lsdfls",
      "rating":"6.0",
      
    }
  }
  	public ArrayList<LiveSession> getSession(String cusId) {
      	ArrayList<String> sessionIds = backend.getSessionIdsByCusId(cusId);
        ArrayList<LiveSession> sessions = new ArrayList<LiveSession>();
      	for(String id: sessionIds) {
          	sessions.add(backend.getLiveSessionInfo(id));
        }
      	return sessions;
    }
  	//获取除课程之外的全部用户个人信息，包括会员相关信息
  {
    "request":"getCustomerById",
    "payload":{
      "cusId":"Csfos",
    }
  }
  {
    "status":"success",
    "payload": {
      "cusId":"Csldf",
      "name":"jlskfjl",
      "password":"sfs",
      
    }
  }
  	public Customer getCusInfoByCusId(String cusId) {
      	String json = backend.getCustomerById(cusId);
      	Customer cus = (Customer)jsonToBean(json, Customer.class);
      	return cus;
    }
    //获取用户会员到期时间，方便到期提醒
  {
    "request":"getExpireTimeByCusId",
    "payload":{
      "cusId":"Csdf"
    }
  }
  {
    "status":"success",
    "payload": {
      "Expiretime":"2020-05-09"
    }
  }
  	public Date getExpireTimeByCusId(String cusId) {
      	String json = backend.getExpireTimeById(cusId);
      	JSONObject jsonObject = JSONObject.fromObject(json);
      	String[] dateformat = new String[]{"yyyy-MM-dd"};
      	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateformat));
      	Date date = (Date)jsonToBean(json, Date.class);
      	return date;
    }
    //获取用户看过的课程
  
  	public ArrayList<Videos> getVideosByCusId(String cusId) {
      	ArrayList<String> videoIds = backend.getVideoIdsbyCusId(cusId);
      	ArrayList<Videos> videos = new ArrayList<Videos>();
      	for(String id : videoIds) {
          	videos.add(backend.getVideosInfo(id));
        }
      	return videos;
    }
}
```

```java
// for administrator
public class PersonalController {
  	Backend backend;
  	public PersonalController(Backend backend) {
      	this.backend = backend;
    }
    // 针对会员的操作
  {
    "request":"addMembership",
    "payload":{
      
    }
  }
  	public void AddMemberShip(String request) {
      	if(backend.addMemberShip(request) == "200") {
          	JOptionPane.showMessageDialog(null, "Add successfully");
        }	
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to add");
        }
    }
  	public void ModifyMemberShip(String request) {
      	if(backend.modifyMemberShip(request) == "200") {
          	JOptionPane.showMessageDialog(null, "Modify successfully");
        }	
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to add");
        }
    }
  	//针对用户的操作
  
  	public ArrayList<Customer> getAllCustomer() {
      	String json = backend.getAllCutomers();
      	CustomerList = (CustomerList)jsonToBean(json, CustomerList.class);
      	return CutomerList.toArrayList();
    }
    //根据ID获取用户
  	public Customer getCusInfoById(String cusId) {
      	String json = backend.getCustomerById(cusId);
      	Customer cus = (Customer)jsonToBean(json, Customer.class);
      	return cus;
    }
    //根据名字获取用户
  	public Customer getCusInfoByName(String cusName) {
      	String json = backend.getCustomerByName(cusName);
      	Customer cus = (Customer)jsonToBean(json, Customer.class);
      	return cus;
    }
  	//根据性别获取用户
  	public ArrayList<Customer> getCusByGender(char gender) {
      	String json = backend.getCutomesByGender();
      	CustomerList = (CustomerList)jsonToBean(json, CustomerList.class);
      	return CutomerList.toArrayList();
    }
    //获取特定会员等级的人数
  {
    "request":"getCusNumByLevel",
    "payload":{
      "membershipLevel":"L1"
    }
  }
  {
    "status":"success",
    "payload":{
      "num":"100"
    }
  }
  	public int getCusNumByLevel(String level) {
      	String json = backend.getAllCutomersByLevel();
      	CustomerList = (CustomerList)jsonToBean(json, CustomerList.class);
      	return CutomerList.length();
    }
  	//针对videos的操作
  {
    "request":"addVideo",
    "payload":{
      "videoId":"V123",
      "videoName":"sdfl",
      "url":"/usr/local",
      "rating":"6.5"
    }
  }
  {
    "status":"success"/"fail"
  }
  	public void AddVideo(String request) {
      	if(backend.addVideo(request) == "200") {
          	JOptionPane.showMessageDialog(null, "Add successfully");
        }	
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to add");
        }
    }
   //修改video
  {
    "request":"modifyVideo",
    "payload": {
      "videoId":"V123",
      "videoName":"sdfl",
      "url":"/usr/local",
      "rating":"6.5"
    }
  }
  {
    "status":"success"/"fail"
  }
  	public void modifyVideo(String request) {
      	if(backend.modifyVideo(request) == "200") {
          	JOptionPane.showMessageDialog(null, "Modify successfully");
        }	
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to modify");
        }
    }
    //获取月收入
  {
    "request":"getMonthlyIncome",
    "payload":{
      "month":"June"
    }
  }
  {
    "status":"success",
    "payload":{
      "income":"2022220"
    }
  }
  	public int getMonthlyIncome(int month) {
      	return backend.getIncomeByMonth(month);
    }
  	//针对类别的操作
  {
    "request":"addCategory",
    'payload': {
      ""
    }
  }
  	public void addCategory(String request) {
      	if(backend.addCategory(request) == "200") {
          	JOptionPane.showMessageDialog(null, "Add successfully");
        }	
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to add");
        }
    }
}
```

```java
public class VideoController {
  	Backend backend;
  	public VideoController(Backend backend) {
      	this.backend = backend;
    }
    //获取所有videos
  	public ArrayList<Video> getAllVideos() {
      	String json = backend.getAllVideos();
      	VideoList = (VideoList)jsonToBean(json, VideoList.class);
      	return VideoList.toArrayList();
    }
   //根据类别获取video
  {
    "request":"getVideosByCategory",
    "payload":{
      "category":"Yoga"
    }
  }
  {
    "status":"success",
    "payload":{
      "videoIds":["V001","V002"]
    }
  }
  	public ArrayList<Video> getAllVideosByCategory(String category) {
      	String json = backend.getAllVideosByCategory();
      	VideoList = (VideoList)jsonToBean(json, VideoList.class);
      	return VideoList.toArrayList();
    }
    //喜欢video
  {
    "request":"likeVideo",
    "payload": {
      "videoId":"V001"
    }
  }
  {
    "status":"success",
  }
  	public void LikeVideo(String videoId) {
      	if(backend.likeVideo(videoId) == "200") {
          	JOptionPane.showMessageDialog(null, "Like successfully");
        }
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to like");
        }
    }
    //videos加到喜爱列表
  {
    "request":"addVideoToFavourite",
    "payload":{
      "cusId":"C001",
      "videoId":"V001"
    }
  }
  {
    "status":"success"
  }
  	public void AddVideoToFavourite(String cusId, String videoId) {
      	if(backend.addVideoToFavourite(cusId, videoId) == "200") {
          	JOptionPane.showMessageDialog(null, "Add successfully");
        }
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to add");
        }
    }
   //评论videos
  {
    "request":"commentOnVideo",
    "payload": {
      "comment":"gc is needed",
      "cusId":"C001",
      "videoId":"V001"
    }
  }
  {
    "status":"success"
  }
  	public void CommentOnVideo(String commment, String cusId, String videoId) {
      	if(backend.commentOnVideo(comment, cusId, videoId) == "200") {
          	JOptionPane.showMessageDialog(null, "Comment successfully");
        }
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to comment");
        }
    }
   //获取比较火的videos
  {
    "request":"getHotVideos",
    "payload": {
      "thresold":"10"
    }
  }
  {
    "status":"success",
    "payload": {
      "videoIds":["V001", "V002"]
    }
  }
  	public ArrayList<Video> getHotVideos() {
      	String json = backend.getHotVideos();
      	VideoList = (VideoList)jsonToBean(json, VideoList.class);
      	return VideoList.toArrayList();
    }
}
```

```java
public class LiveController {
  	Backend backend;
  	public LiveController(Backend backend) {
      	this.backend = backend;
    }
  	//获取所有live session
  	public ArrayList<LiveSession> getAllLiveSessions() {
      	String json = backend.getAllLiveSessions();
      	LiveSessionList = (LiveSessionList)jsonToBean(json, LiveSessionList.class);
      	return LiveSessionList.toArrayList();
    }
    //根据类别获取live session
  	public ArrayList<LiveSession> getAllLiveSessionsByCategory(String cateogry) {
      	String json = backend.getAllLiveSessionsByCategory(category);
      	LiveSessionList = (LiveSessionList)jsonToBean(json, LiveSessionList.class);
      	return LiveSessionList.toArrayList();
    }
    //返回某个live session剩余可预订个数
  {
    "request":"getRemainingNumOfSession",
    "payload": {
      "liveSessionId":"L001"
    }
  }
  {
    "status": "success",
    "payload":{
      "num":"10"
    }
  }
  	public int getAvailableNumOfSession(String liveSessionId) {
      	return backend.getAvailabeNumOfSession(liveSessionId);
    }
   //预订live session
  {
    "request": "bookLiveSession",
    "payload":{
      "cusId":"C001",
      "liveSessionId":"L001"
    }
  }
  {
    "status":"success"
  }
  	public void bookLiveSession(String cusId, String liveSessionId) {
      	if(getAvailableNumOfSession(liveSessinoId) != 0 && backend.bookLiveSession() == "200") {
          	JOptionPane.showMessageDialog(null, "Book successfully");
        }
      	else {
          	JOptionPane.showMessageDialog(null, "Fail to book");
        }
    }
  	
}
```

```java
public class SearchPageController
{
  Backend backend;//
  ArrayList<Video> videos;
  public SearchPageController(ArrayList<Video> videos，Backend backend)
  {
    this.videos = videos;
 		this.backend = backend;
  }
  public void updateVideo()
  {
    // 我个人认为之前这个updateVideo()就是根据类别查询
    // 所以我在这里换成查询全部课程
    ArrayList<String> idList = backend.getAllVideo();
    for i : idList
    {
      videos.append(new Video(backend.getVideoInfo()));
    }
    // 具体的逻辑    
  }
  
  public void SearchVideoByID(String videoID)
  {
    // 每次新的查询都会先清除上一次的，然后将查询的结果给到Videos
    // page直接读取新的Videos就可以得到查询结果
    videos.clear();
    String json = backend.getVideoByID(videoID);
    Video video = (Video)jsonToBean(json, Video.class);
  }
 
  public void SearchVideoByName(String videoName)
  {
    videos.clear();
    String json = backend.getVideoByName(videoName);
    Video video = (Video)jsonToBean(json, Video.class);
  }
    
  public void SearchVideoByCategory(String category)
  {
    videos.clear();
    String json = backend.getVideoByCategory(category);
    Video video = (Video)jsonToBean(json, Video.class);
  }
    
  public void SearchVideoByKeyWord(String Keyword)
  {
    String json  = backend.getVideoByKeyWord(Keyword);
    Video video = (Video)jsonToBean(json, Video.class);
  }
  
  public void SearchVideoByLevel(String level)
  {
      // 根据训练难度来查找视频，如只想要初级的
      // 这个可以不需要用户主动输入，而是咱们给出标签，点击标签自动搜索
  }
  

  
  public void Search video for VIP( )
  {
  // 针对VIP用户，查找专属于vip的视频
  }
  {
    "request":"deleteVideoById",
    "payload":{
      "videoId":"V001"
    }
  }
  {
    "status":"success"
  }
  public void DeleteVideo((ArrayList<Video> videos)
  {
      // 管理员通过ID、名字或者关键词搜到视频，然后得到Videos
      // 再通过Videos并调用此函数将视频删除
   // 再跟backend交互，把视频从后端删除
  }
                          
                                         
  public void SortVideos(ArrayList<Video> videos)
  {
      // 传入Videos的ArrayList，然后对ArrayList进行排序，这样就得到了排序后的video列表
      // 如果取消排序就只需要new一个新的，或者清空ArrayList并重新调用updateVideo()
      // 个人感觉第一次迭代不需要做到排序，所以这里就没有细分，其实可以按照难易程度、热度等等进行排序
  }
  
  
}
```

