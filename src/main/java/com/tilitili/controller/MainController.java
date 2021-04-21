package com.tilitili.controller;

import com.tilitili.common.entity.VideoData;
import com.tilitili.common.entity.view.BaseModel;
import com.tilitili.entity.request.MainRequest;
import com.tilitili.entity.response.MainResponse;
import com.tilitili.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/main")
public class MainController extends BaseController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("")
    @ResponseBody
    public BaseModel main() {
        MainResponse response = new MainResponse();
        response.setRankList(mainService.getRankList());
        return BaseModel.success(response);
    }

    @GetMapping("/recommend")
    @ResponseBody
    public BaseModel mainRecommend(MainRequest request) {
        List<VideoData> recommendItemList = mainService.getRecommendList();
        return BaseModel.success(recommendItemList);
    }

}
