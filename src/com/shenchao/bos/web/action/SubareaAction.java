package com.shenchao.bos.web.action;

import com.shenchao.bos.domain.Region;
import com.shenchao.bos.domain.Subarea;
import com.shenchao.bos.service.ISubareaService;
import com.shenchao.bos.utils.FileUtils;
import com.shenchao.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by shenchao on 2016/11/26.
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    public String add() {
        subareaService.add(model);
        return "list";
    }

    public String pageQuery() throws Exception {
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        Region region = model.getRegion();
        if (StringUtils.isNotBlank(model.getAddresskey())) {
            detachedCriteria.add(Restrictions.like("addressKey", "%"+model.getAddresskey()+"%"));
        }
        if (region != null) {
            //创建别名，用于多表查询
            detachedCriteria.createAlias("region", "r");
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            if (StringUtils.isNotBlank(province)) {
                detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
            }
            if (StringUtils.isNotBlank(city)) {
                detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
            }
            if (StringUtils.isNotBlank(district)) {
                detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
            }
        }

        subareaService.pageQuery(pageBean);
        this.writePageBean2Json(pageBean,new String[]{"currentPage","pageSize","detachedCriteria","decidedzone","subareas"});
        return NONE;
    }
    /**
     * 使用POI写入Excel文件，提供下载
     * @throws IOException
     */
    public String exportXls() throws IOException {
        List<Subarea> list = subareaService.findAll();
        // 在内存中创建一个Excel文件，通过输出流写到客户端提供下载
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个sheet页
        HSSFSheet sheet = workbook.createSheet("分区数据");
        // 创建标题行
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("区域编号");
        headRow.createCell(2).setCellValue("地址关键字");
        headRow.createCell(3).setCellValue("省市区");

        for (Subarea subarea : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
            dataRow.createCell(2).setCellValue(subarea.getAddresskey());
            Region region = subarea.getRegion();
            dataRow.createCell(3).setCellValue(region.getProvince()+region.getCity()+region.getDistrict());
        }

        String filename = "分区数据.xls";
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        filename = FileUtils.encodeDownloadFilename(filename, agent);
        //一个流两个头
        ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
        String contentType = ServletActionContext.getServletContext().getMimeType(filename);
        ServletActionContext.getResponse().setContentType(contentType);
        ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
        workbook.write(out);
        return NONE;
    }

    /**
     * 查询没有关联到定区的分区数据，返回json
     * @return
     */
    public String listAjax() throws IOException {
        List<Subarea> list = subareaService.findListNotAssociation();
        this.writeList2Json(list,new String[]{"decidedzone","region"});
        return NONE;
    }
}
