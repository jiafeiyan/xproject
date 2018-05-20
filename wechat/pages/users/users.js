//users.js
//获取应用实例
const app = getApp()

Page({
    data: {
        result: [{
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "黑"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "黑"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "红"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "黑"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "红"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "红"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "红"
        },
        ],
        name: '最近走势'
    },
    onLoad: function () {},
    canvasIdErrorCallback: function (e) {
        console.error(e.detail.errMsg)
    },
    onReady: function (e) {
        // 画布高度，与CSS中定义等值
        var canvasHeight = 100;
        // x轴放大倍数
        var ratioX = 30;
        // y轴放大倍数
        var ratioY = 10;
        const context = wx.createCanvasContext('firstCanvas');

        var data = [{
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "黑"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "黑"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "红"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "黑"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "红"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "红"
        },
        {
            "EventType": "世界杯",
            "RecommendContent": "全场比分大",
            "EventResult": "德国3：1西班牙",
            "RecommendFlag": "红"
        },
        ];

        for (var i = 0; i < data.length - 1; i++) {
            // 当前点坐标
            if (data[i].RecommendFlag == "红") {
                var currentPoint = {
                    x: i * ratioX,
                    y: 50
                };
            } else {
                var currentPoint = {
                    x: i * ratioX,
                    y: 100
                };
            }
            // 下一个点坐标
            if (data[i + 1].RecommendFlag == "红") {
                var nextPoint = {
                    x: (i + 1) * ratioX,
                    y: 50
                };
            } else {
                var nextPoint = {
                    x: (i + 1) * ratioX,
                    y: 100
                };
            }
            /* 画折线 */
            if (i < data.length - 1) {
                // 开始路径
                context.beginPath();
                // 移动到当前点
                context.moveTo(currentPoint.x, currentPoint.y);
                // 画线到下个点
                context.lineTo(nextPoint.x, nextPoint.y);
                // 设置属性
                context.setLineWidth(3);
                // 设置颜色
                context.setStrokeStyle('#7587db');
                // 描线
                context.stroke();
            }
        }
        context.draw();

    }
})