package edu.hanoi.service.dao;

import edu.hanoi.service.model.Group;

import java.util.List;

/**
 * Created by trungdovan on 12/4/16.
 */
public interface GroupDAO {
    List<Group> list();

    void insert(Group userGroup);
}
