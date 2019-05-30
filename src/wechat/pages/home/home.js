const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    searchinput: '',
    dots: true,
    autoplay: true,
    dtime: 3000,
    swipers: ['index-swiper1.png', 'index-swiper2.png', 'index-swiper3.png'],
  },
  /**
   * 自定义函数
   */
  onTapInformation: function() {
    if(wx.getStorageSync('X-Token')) {
      wx.navigateTo({
        url: '../information/information',
      })
    } else {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },

  onTapMymuseum: function() {
    var auth = ''
    if (wx.getStorageSync('X-Token')) {
      wx.request({
        url: app.globalData.host + '/user/' + wx.getStorageSync('uid'),
        header: {
          'X-Token': wx.getStorageSync('X-Token')
        },
        method: 'GET',
        success(res) {
          auth = res.data.data.auth
          console.log(auth)
          wx.navigateTo({
            url: '../mymuseum/mymuseum?auth=' + auth
          })
        },
        fail() {
          console.log('Getting Information Page Fail')
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

  onTapFavorite: function() {
    if (wx.getStorageSync('X-Token')) {
      wx.navigateTo({
        url: '../favorite/favorite',
      })
    } else {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },

  onTapExhibit: function (e) {
    var sid = e.currentTarget.dataset.sid;
    console.log("Open page exhibition_" + sid);
    wx.navigateTo({
      url: '../exhibition/exhibition?sid=' + sid
    })
  },

  onTapAppointment: function () {
    if (wx.getStorageSync('X-Token')) {
      wx.navigateTo({
        url: '../appointment/appointment',
      })
    } else {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    console.log('Opening Home Page')
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.login({
        success: function (log) {
          wx.getUserInfo({
            success: function (res) {
              if (log.code) {
                console.log(log.code)
                console.log(res.iv)
                wx.request({
                  url: app.globalData.url + '/user/login',
                  data: {
                    code: log.code,
                    encryptedData: res.encryptedData,
                    iv: res.iv
                  },
                  method: 'POST',
                  success(res) {
                    console.log(log.code)
                    console.log(res.data)
                  }
                })
              } else {
              }
              app.globalData.userInfo = res.userInfo
              this.setData({
                userInfo: res.userInfo,
                hasUserInfo: true
              })
            }
          })
        },
        fail: function (res) { },
        complete: function (res) { },
      })
    }
  },

  getUserInfo: function (e) {
    var getUserInfoThis = this
    wx.login({
      success: function (log) {
        if (log.code) {
          wx.request({
            url: app.globalData.host + '/user/login',
            data: {
              code: log.code,
              encryptedData: e.detail.encryptedData,
              iv: e.detail.iv
            },
            method: 'POST',
            success(res) {
              console.log(res)
              app.globalData.userInfo = e.detail.userInfo
              getUserInfoThis.setData({
                userInfo: e.detail.userInfo,
                hasUserInfo: true
              })
              wx.setStorageSync('X-Token', res.header['X-Token'])
              wx.setStorageSync('uid', res.data.data.uid)
              console.log('login success')
              console.log(wx.getStorageSync('uid'))
            },
            fail(res) {
              console.log('request fail')
            }
          })
        } else { }
      },
      fail: function (res) { },
      complete: function (res) { },
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