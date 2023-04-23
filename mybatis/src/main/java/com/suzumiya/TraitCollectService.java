package com.suzumiya;

import com.suzumiya.dao.ITraitCollectDao;
import com.suzumiya.model.TraitCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TraitCollectService {

    @Autowired
    ITraitCollectDao traitCollectDao;

    @Transactional
    public void insertOrUpdate(TraitCollect traitCollect){
        List<TraitCollect> allByJobComponent = traitCollectDao.findAllByJobComponent(traitCollect);
        if (allByJobComponent.isEmpty()){
            traitCollectDao.add(traitCollect);
        }else {
            traitCollectDao.update(traitCollect);
        }
    }
}
