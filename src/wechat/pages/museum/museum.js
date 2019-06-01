// pages/museum/museum.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    show_list: [],
    exhibition: {},
    comment_list: [],
    museum_introduce: "",
    museum_introduce_text: "",
    museum_introduce_length: 0,
    museum_introduce_full: true,
    image_bk_url: "/image/exhibition1.png",
    image_at_url: "/image/avatar.png",
    intro_fullText: false,
    intro_tip: "展开全文",
    intro_icon: "/image/icon/more.png",
    intro_s: "",
    intro_text: "陕西历史博物馆位于西安大雁塔的西北侧，筹建于1983年，1991年6月20日落成开放，是中国第一座大型现代化国家级博物馆，它的建成标志着中国博物馆事业迈入了新的发展里程。这座馆舍为“中央殿堂、四隅崇楼”的唐风建筑群，主次井然有序，高低错落有致，气势雄浑庄重，融民族传统、地方特色和时代精神于一体。馆区占地 65000平方米。建筑面积55600平方米，文物库区面积8000平方米，展厅面积11000平方米，馆藏文物1717950件（组）。上起远古人类初始阶段使用的简单石器，下至1840年前社会生活中的各类器物，时间跨度长达一百多万年。文物不仅数量多、种类全，而且品位高、价值广，其中的商周青铜器精美绝伦，历代陶俑千姿百态，汉唐金银器独步全国，唐墓壁 画举世无双。可谓琳琅满目、精品荟萃。"
  },
  /**
   * 自定义函数
   */
  onTapExhibit: function (e) {
    var sid = e.currentTarget.dataset.sid;
    console.log("Open page exhibition_" + sid);
    wx.navigateTo({
      url: '../exhibition/exhibition?sid=' + sid
    })
  },

  fullText: function () {
    this.intro_fullText = !this.intro_fullText;
    if (this.intro_fullText === true) {
      this.setData({
        intro_s: this.data.intro_text,
        intro_tip: "收起全文",
        intro_icon: "/image/icon/less.png"
      })
    } else {
      this.setData({
        intro_s: this.data.intro_text.substring(0, 90) + "...",
        intro_tip: "展开全文",
        intro_icon: "/image/icon/more.png"
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    console.log('Opening Museum Page')
    var museumThis = this
    wx.request({
      url: app.globalData.host + '/exhibition/' + e.eid + '/exhibition_content',
      method: 'GET',
      success(res) {
        console.log(res)
        museumThis.setData({
          exhibition: res.data.data.exhibition,
          show_list: res.data.data.show_List,
          comment_list: res.data.data.comment_list,
          museum_introduce: res.data.data.exhibition.introduce,
          museum_introduce_text: res.data.data.exhibition.introduce
        })
        if (museumThis.data.museum_introduce_length > 90) {
          museumThis.setData({
            museum_introduce: museumThis.data.museum_introduce_text.substring(0, 90) + "...",
            museum_introduce_full: false
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})