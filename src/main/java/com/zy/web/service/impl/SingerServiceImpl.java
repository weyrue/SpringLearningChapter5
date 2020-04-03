package com.zy.web.service.impl;

import com.zy.mysql.mybatis.page.Page;
import com.zy.web.dao.SingerMapper;
import com.zy.web.entities.SingerPO;
import com.zy.web.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service("singerService")
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;
//    @Autowired
//    private TransactionTemplate transactionTemplate;

    @Override
    public List<SingerPO> findAll() {
        return singerMapper.findByList(new SingerPO());
    }

    @Override
    public SingerPO findById(Long id) {
        return singerMapper.get(id);
    }

    @Override
    @Transactional
    public void save(SingerPO po) {
//        transactionTemplate.execute(new TransactionCallback<SingerPO>() {
//            @Override
//            public SingerPO doInTransaction(TransactionStatus transactionStatus) {
//                System.out.println("SingerPO before insert: ");
//                System.out.println(po);
//                singerMapper.insert(po);
//                System.out.println("SingerPO after insert: ");
//                System.out.println(po);
//                throw new RuntimeException("回退");
////                return po;
//            }
//        });

        System.out.println("SingerPO before insert: ");
        System.out.println(po);
        singerMapper.insert(po);
        System.out.println("SingerPO after insert: ");
        System.out.println(po);
        throw new RuntimeException("回退");

    }

    @Override
    public List<SingerPO> findByPage(Page<SingerPO> page, SingerPO po) {
        return singerMapper.findByPage(page, po);
    }
}
