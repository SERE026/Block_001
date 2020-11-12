package com.sport.modules.sport.service.impl;

import com.sport.common.utils.PageResult;
import com.sport.common.utils.Query;
import com.sport.modules.sport.dao.BmiGradeMapper;
import com.sport.modules.sport.entity.BmiGrade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sport.modules.sport.service.BmiGradeService;


@Service("bmiGradeService")
public class BmiGradeServiceImpl extends ServiceImpl<BmiGradeMapper, BmiGrade> implements BmiGradeService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        IPage<BmiGrade> page = this.page(
                new Query<BmiGrade>().getPage(params),
                new QueryWrapper<BmiGrade>()
        );

        return new PageResult(page);
    }

    @Override
    public List<BmiGrade> getByStudentId(Integer studentId) {
        BmiGrade bmiGrade = BmiGrade.builder().studentId(studentId).build();
        QueryWrapper queryWrapper = new QueryWrapper(bmiGrade);
        return this.baseMapper.selectByStudentId(studentId);
    }

    @Override
    public BmiGrade getLastByStuGradeId(Integer stuGradeId) {
        return this.baseMapper.getLastByStuGradeId(stuGradeId);
    }

}
