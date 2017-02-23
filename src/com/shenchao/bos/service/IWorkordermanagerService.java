package com.shenchao.bos.service;

import com.shenchao.bos.domain.Workordermanager;

import java.util.List;

/**
 * Created by shenchao on 2016/12/4.
 */
public interface IWorkordermanagerService {
    void add(Workordermanager workordermanager);

    List<Workordermanager> findListNotStart();

    void start(String id);

    Workordermanager findById(String workordermanageId);

    void checkWorkordermanage(String taskId, Integer check, String workordermanageId);
}
