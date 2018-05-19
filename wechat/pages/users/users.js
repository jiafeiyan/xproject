//users.js
//获取应用实例
const app = getApp()

Page({
    data: {
        result: [
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
            {"EventType": "世界杯", "RecommendContent": "全场比分大", "EventResult": "德国3：1西班牙", "RecommendFlag": "黑"},
        ],
        name:'红军篮彩'
    },
    onLoad: function () {},
    canvasIdErrorCallback: function (e) {
        console.error(e.detail.errMsg)
    },
    onReady: function (e) {
        // 使用 wx.createContext 获取绘图上下文 context
        var context = wx.createCanvasContext('firstCanvas')

        context.setStrokeStyle("#00ff00")
        context.setLineWidth(5)
        context.rect(0, 0, 200, 200)
        context.stroke()
        context.setStrokeStyle("#ff0000")
        context.setLineWidth(2)
        context.moveTo(160, 100)
        context.arc(100, 100, 60, 0, 2 * Math.PI, true)
        context.moveTo(140, 100)
        context.arc(100, 100, 40, 0, Math.PI, false)
        context.moveTo(85, 80)
        context.arc(80, 80, 5, 0, 2 * Math.PI, true)
        context.moveTo(125, 80)
        context.arc(120, 80, 5, 0, 2 * Math.PI, true)
        context.stroke()
        context.draw()
    }
})