// pages/index/exhibition.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    intime: false,
    collection_list: [],
    comment_list: [],
    show: {},
    show_introduce: "",
    show_introduce_text: "",
    show_introduce_length: 0,
    show_introduce_full: true,
    star_status: 0,
    like_status: false,
    like_icon: "/image/icon/like.png",
    star_status: false,
    star_icon: "/image/icon/star.png",
    exhibition_order: false,
    exhibition_order_type: "",
    exhibition_order_status: "",
    image_url: "/image/exhibition1.png",
    exhibition_name: "齐白石真迹珍品展",
    exhibition_museum: "李白好帅展品馆",
    exhibition_address: "湖南省长沙市天心区",
    exhibition_time: "冬季 8:00-17:00 | 夏季 7:00- 18:00",
    exhibition_fullText: false,
    introduce_tip: "展开全文",
    introduce_tip_icon: "/image/icon/more.png",
    exhibition_introduce: "",
    exhibition_introduce_text: "原名纯芝，字渭青，号兰亭。后改名璜，字濒生，号白石、白石山翁、老萍、饿叟、借山吟馆主者、寄萍堂上老人、三百石印富翁。是近现代中国绘画大师，世界文化名人。早年曾为木工，后以卖画为生，五十七岁后定居北京。擅画花鸟、虫鱼、山水、人物，笔墨雄浑滋润，色彩浓艳明快，造型简练生动，意境淳厚朴实。所作鱼虾虫蟹，天趣横生。齐白石书工篆隶，取法于秦汉碑版，行书饶古拙之趣，篆刻自成一家，善写诗文。曾任中央美术学院名誉教授、中国美术家协会主席等职。代表作有《蛙声十里出山泉》《墨虾》等。著有《白石诗草》《白石老人自述》等。",
  },

  /**
   * 自定义函数
   */
  onComment: function (e) {
    var showThis = this
    console.log(e.detail.value.comment)
    if (wx.getStorageSync('X-Token')) {
      wx.request({
        url: app.globalData.host + '/object_type/show/object_id/' + showThis.data.show.sid + '/comment',
        header: {
          'X-Token': wx.getStorageSync('X-Token')
        },
        data: {
          content: e.detail.value.comment
        },
        method: 'POST',
        success(res) {
          console.log(res)
        },
        fail() {
        }
      })
    } else {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },

  onShareAppMessage: function (e) {
    return {
      title: 'MShow',
      path: 'pages/index/index',
      success: function (res) {
        // 转发成功
        console.log("转发成功:" + JSON.stringify(res));
      },
      fail: function (res) {
        // 转发失败
        console.log("转发失败:" + JSON.stringify(res));
      }
    }
  },

  changeOrder: function () {
    var showThis = this
    this.exhibition_order = !this.exhibition_order;
    if (wx.getStorageSync('X-Token')) {
      if (this.exhibition_order === true) {
        wx.showActionSheet({
          itemList: ['9:00-11:30', '2:30-5:00'],
          success: function (res) {
            if (!res.cancel) {
              console.log(res.tapIndex)//这里是点击了那个按钮的下标
              wx.request({
                url: app.globalData.host + '/show/' + showThis.data.show.sid + '/appointment',
                header: {
                  'X-Token': wx.getStorageSync('X-Token')
                },
                method: 'POST',
                data: {
                  arrival_time: res.tapIndex
                },
                success(res) {
                  showThis.setData({
                    exhibition_order_type: "default",
                    exhibition_order_status: "已预约"
                  })
                },
                fail() {
                }
              })
            }
          }
        })
      } else {
        wx.request({
          url: app.globalData.host + '/show/' + showThis.data.show.sid + '/book_type/cancel/appointment_/' + showThis.data.show.sid,
          header: {
            'X-Token': wx.getStorageSync('X-Token')
          },
          success(res) {
            showThis.setData({
              exhibition_order_type: "primary",
              exhibition_order_status: "预约"
            })
          },
          fail() {
          }
        })
      }
    } else {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },

  fullText: function () {
    this.data.show_introduce_full = !this.data.show_introduce_full;
    if (this.exhibition_fullText === true) {
      this.setData({
        show_introduce: this.data.show_introduce_text,
        introduce_tip: "收起全文",
        introduce_tip_icon: "/image/icon/less.png"
      })
    } else {
      this.setData({
        show_introduce: this.data.show_introduce_text.substring(0, 90) + "...",
        introduce_tip: "展开全文",
        introduce_tip_icon: "/image/icon/more.png"
      })
    }
  },

  changeLike: function () {
    if (wx.getStorageSync('X-Token')) {
      this.like_status = !this.like_status;
      if (this.like_status === true) {
        this.setData({
          like_icon: "/image/icon/like-h.png"
        })
      } else {
        this.setData({
          like_icon: "/image/icon/like.png"
        })
      }
    } else {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },

  changeStar: function () {
    if (wx.getStorageSync('X-Token')) {
      this.star_status = !this.star_status;
      if (this.star_status === true) {
        this.setData({
          star_icon: "/image/icon/star-h.png"
        })
      } else {
        this.setData({
          star_icon: "/image/icon/star.png"
        })
      }
    } else {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },

  onAllCollections: function() {
    var sid = this.data.show.sid
    var collectionsBean = JSON.stringify(this.data.collection_list)
    wx.navigateTo({
      url: '../collections/collections?collectionsBean=' + collectionsBean
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    console.log("Opening Exhibition " + e.sid);
    var showThis = this
    wx.request({
      url: app.globalData.host + '/show/' + e.sid +'/show_content',
      method: 'GET',
      success(res) {
        wx.request({
          url: app.globalData.host + '/exhibition/' + res.data.data.show.eid + '/exhibition_content',
          method: 'GET',
          success(exh) {
            showThis.setData({
              exhibition_museum: exh.data.data.exhibition.name
            })
          }
        })
        if (wx.getStorageSync('X-Token')) {
          wx.request({
            url: app.globalData.host + '/object_type/exhibition/object_id/' + res.data.data.show.eid + '/star',
            method: 'GET',
            header: {
              'X-Token': wx.getStorageSync('X-Token'),
            },
            success(star) {
              console.log(star)
              showThis.setData({
                star_status: star.data.data.star_status
              })
            }
          })
        }
        
        // wx.request({
        //   url: app.globalData.host + '/comment_list/object_type/' + 'show' + '/object_id/' + res.data.data.show.sid,
        //   method: 'GET',
        //   success(com) {
        //     showThis.setData({
        //       comment_list: com.data.data.comment_list
        //     })
        //   }
        // })
        showThis.setData({
          comment_list: res.data.data.comment_list,
          collection_list: res.data.data.collection_list,
          show: res.data.data.show,
          show_introduce_text: res.data.data.show.introduce,
          show_introduce_length: res.data.data.show.introduce.length
        })
        if (showThis.data.show_introduce_length > 90) {
          showThis.setData({
            show_introduce: showThis.data.show.introduce.substring(0, 90) + "...",
            show_introduce_full: false
          })
        } else {
          showThis.setData({
            show_introduce: showThis.data.show.introduce
          })
        }
      }
    })
    // if(wx.getStorageSync('X-Token')) {
    //   wx.request({
    //     url: app.globalData.host + '/user/' + wx.getStorageSync('uid'),
    //     header: {
    //       'X-Token': wx.getStorageSync('X-Token')
    //     },
    //     method: 'GET',
    //     success(res) {
    //     },
    //     fail() {
    //     }
    //   })
    // }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.setData({
      exhibition_introduce: this.data.exhibition_introduce_text.substring(0, 90) + "...",
      exhibition_order: false,
      exhibition_order_type: "primary",
      exhibition_order_status: "预约"
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