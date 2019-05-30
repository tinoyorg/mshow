// pages/mymuseum/mymuseum.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    auth: '',
    isauth: false,
    exhibitionInfo: {},
    exhibition_introduce: "",
    exhibition_introduce_text: "",
    exhibition_introduce_length: 0,
    exhibition_introduce_full: true,
    image_bk_url: "/image/museum1.png",
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
  deleteMyMuseum: function () {
    var mymuseumThis = this
    wx.request({
      url: app.globalData.host + '/exhibition/' + mymuseumThis.data.exhibitionInfo.eid + '/exhibition_info',
      method: 'DELETE',
      header: {
        'X-Token': wx.getStorageSync('X-Token')
      },
      success(res) {
        console.log(res)
        console.log('Delete Success')
      },
    })
  },

  fullText: function () {
    this.data.exhibition_introduce_full = !this.data.exhibition_introduce_full;
    if (this.intro_fullText === true) {
      this.setData({
        exhibition_introduce: this.data.exhibition_introduce_full,
        intro_tip: "收起全文",
        intro_icon: "/image/icon/less.png"
      })
    } else {
      this.setData({
        exhibition_introduce: this.data.exhibition_introduce_full.substring(0, 90) + "...",
        intro_tip: "展开全文",
        intro_icon: "/image/icon/more.png"
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    var mumuseumThis = this
    var length = 0
    console.log('Opening Museum Page')
    this.setData({
      auth: e.auth
    })
    if(e.auth != 'admin') {
      wx.showModal({
        title: '提示',
        content: '您还没有创建展馆，是否创建？',
        confirmText: '创建',
        success: function (res) {
          if (res.confirm) {//这里是点击了确定以后
            console.log('用户点击确定')
          } else {//这里是点击了取消以后
            console.log('用户点击取消')
          }
        }
      })
    } else {
      wx.request({
        url: app.globalData.host + '/user/' + wx.getStorageSync('uid'),
        header: {
          'X-Token': wx.getStorageSync('X-Token')
        },
        method: 'GET',
        success(res) {
          console.log(res)
          if (res.data.data.exhibition_List[0].introduce == null) {
            length = 0
          } else {
            length = res.data.data.exhibition_List[0].introduce.length
          }
          mumuseumThis.setData({
            exhibitionInfo: res.data.data.exhibition_List[0],
            exhibition_introduce_length: length,
            exhibition_introduce_text: res.data.data.exhibition_List[0].introduce
          })
          if (mumuseumThis.data.show_introduce_length > 90) {
            mumuseumThis.setData({
              exhibition_introduce: res.data.data.exhibition_List[0].introduce.substring(0, 90) + "...",
              exhibition_introduce_full: false
            })
          } else {
            mumuseumThis.setData({
              exhibition_introduce: res.data.data.exhibition_List[0].introduce
            })
          }
          console.log(mumuseumThis.data.exhibitionInfo)
        },
        fail() {
          console.log('Getting Information Fail')
        }
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.setData({
      intro_s: this.data.intro_text.substring(0, 90) + "...",
      intro_fullText: false,
    });
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