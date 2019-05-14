package io.renren.modules.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageResult;
import io.renren.common.utils.Query;
import io.renren.modules.sport.dao.StudentMapper;
import io.renren.modules.sport.dto.StudentDTO;
import io.renren.modules.sport.entity.Student;
import io.renren.modules.sport.service.StudentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        String realname = (String) params.get("realname");
        IPage<Student> page = this.page(
                new Query<Student>().getPage(params),
                new QueryWrapper<Student>()
                        .eq(StringUtils.isNotBlank(realname),"realname",realname)
        );

        return new PageResult(page);
    }

    @Override
    public StudentDTO getStudentWithSchoolNameById(Integer studentId) {
        return this.baseMapper.getStudentWithSchoolNameById(studentId);
    }

    @Override
    public String getStudentNumber() {
        return baseMapper.getStudentNumber();
    }

}
