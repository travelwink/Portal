package travelwink.manage.service;

import travelwink.manage.domain.entity.Content;
import travelwink.manage.domain.entity.Navigation;
import travelwink.manage.domain.entity.Page;

import java.util.List;

public interface ReleaseService {


    int addContent(Content content);

    List<Content> findAll();

    Page findPageById(int id);

    Navigation findParent(Navigation navigation);
}
