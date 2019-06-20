package com.fu.blog.service;

import com.fu.blog.NotFoundException;
import com.fu.blog.dao.TypeRespository;
import com.fu.blog.po.Type;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fudada
 * @date 2019/6/20 - 14:37
 */

@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeRespository typeRespository;


    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRespository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRespository.findById(id).get();
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRespository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRespository.findById(id).get();
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);

        return typeRespository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRespository.deleteById(id);
    }
}
