package com.sport.modules.sport.service.impl;

import com.sport.common.utils.PageResult;
import com.sport.common.utils.Query;
import com.sport.modules.sport.dao.StudentGradeMapper;
import com.sport.modules.sport.entity.StudentGrade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sport.modules.sport.service.StudentGradeService;


@Service("studentGradeService")
public class StudentGradeServiceImpl extends ServiceImpl<StudentGradeMapper, StudentGrade> implements StudentGradeService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        IPage<StudentGrade> page = this.page(
                new Query<StudentGrade>().getPage(params),
                new QueryWrapper<StudentGrade>()
        );

        return new PageResult(page);
    }

    @Override
    public List<StudentGrade> getLastTwoGrade(Integer studentId) {
        return this.baseMapper.getByLastTwoStudentId(studentId);
    }

    @Override
    public StudentGrade getLastGrade(Integer studentId) {
        return this.baseMapper.getByLastStudentId(studentId);
    }

}
