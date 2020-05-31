package com.lxlt.service.adminservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lxlt.bean.Admin;
import com.lxlt.bean.AdminExample;
import com.lxlt.bean.TopicExample;
import com.lxlt.bean.adminbean.AdminReq;
import com.lxlt.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Map<String, Object> queryAdmin(AdminReq adminReq) {
        //分页
        PageHelper.startPage(adminReq.getPage(),adminReq.getLimit());
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        if(StringUtil.isNotEmpty(adminReq.getUsername())){
            criteria.andUsernameLike("%" + adminReq.getUsername() + "%");
        }
        criteria.andDeletedEqualTo(false);
        //根据时间更新
        adminExample.setOrderByClause(adminReq.getSort() + " " + adminReq.getOrder());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        long total = adminPageInfo.getTotal();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("items",admins);
        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map insertAdmin(Admin admin) {
        Map<String, Object> map = new HashMap();
        //对名称格式进行判断
        if(admin.getUsername().length() < 6){
            //管理员名称不符合规定
            map.put("code", 601);
            return map;
        }
        //对名称进行查重
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdGreaterThanOrEqualTo(0);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        for (int i = 0; i < admins.size(); i++) {
            if(admin.getUsername().equals(admins.get(i).getUsername())){
                //管理员已存在
                map.put("code", 602);
                return map;
            }
        }
        //对密码格式进行判断
        if(admin.getPassword().length() < 6){
            //密码不符合规定
            map.put("code", 603);
            return map;
        }
        //设置默认值
        Date date = new Date();
        admin.setAddTime(date);
        admin.setUpdateTime(date);
        admin.setDeleted(false);
        int code = adminMapper.insertSelective(admin);
        if (code != 1){
            map.put("code", 501);
        }
        map.put("code", 200);
        map.put("admin", admin);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map updateAdmin(Admin admin) {
        Map<String, Object> map = new HashMap();
        //对名称格式进行判断
        if(admin.getUsername().length() < 6){
            //管理员名称不符合规定
            map.put("code", 601);
            return map;
        }
        //对名称进行查重
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdNotEqualTo(admin.getId());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        for (int i = 0; i < admins.size(); i++) {
            if(admin.getUsername().equals(admins.get(i).getUsername())){
                //管理员已存在
                map.put("code", 602);
                return map;
            }
        }
        //对密码格式进行判断
        if(admin.getPassword().length() < 6){
            //密码不符合规定
            map.put("code", 603);
            return map;
        }
        admin.setUpdateTime(new Date());
        AdminExample adminExample1 = new AdminExample();
        adminExample1.createCriteria().andIdEqualTo(admin.getId());
        int code = adminMapper.updateByPrimaryKeySelective(admin);
        if (code != 1){
            map.put("code", 501);
        }
        map.put("code", 200);
        map.put("admin", admin);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteAdmin(Admin admin) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdEqualTo(admin.getId());
        admin.setUpdateTime(new Date());
        admin.setDeleted(true);
        int code = adminMapper.updateByPrimaryKeySelective(admin);
        if(code == 0){
            return 502;
        }
        return 200;
    }
}
