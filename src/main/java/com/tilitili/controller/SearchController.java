package com.tilitili.controller;

import com.tilitili.common.entity.VideoData;
import com.tilitili.common.entity.query.VideoDataQuery;
import com.tilitili.common.entity.view.BaseModel;
import com.tilitili.common.entity.view.PageModel;
import com.tilitili.common.manager.VideoDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/search")
public class SearchController {
    private final VideoDataManager videoDataManager;

    @Autowired
    public SearchController(VideoDataManager videoDataManager) {
        this.videoDataManager = videoDataManager;
    }

    @GetMapping("")
    @ResponseBody
    public BaseModel getSearch(VideoDataQuery query) {
        Integer newIssue = videoDataManager.getNewIssue();
        query.setIssue(newIssue).setStatus(0).setIsDelete(false).setSubSorter("av").setSubSorted("desc");
        int total = videoDataManager.count(query);
        List<VideoData> videoDataList = videoDataManager.list(query);
        return PageModel.of(total, query.getPageSize(), query.getCurrent(), videoDataList);
    }
}
