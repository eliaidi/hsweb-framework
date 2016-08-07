package org.hsweb.web.service.draft;

import org.hsweb.web.bean.po.draft.Draft;

import java.util.List;

public interface DraftService {
    String createDraft(String key, Draft draft);

    List<Draft> getAllDraftByKey(String key, String userId);

    boolean removeDraft(String key, String userId, String id);

    boolean removeDraft(String key, String userId);
}
