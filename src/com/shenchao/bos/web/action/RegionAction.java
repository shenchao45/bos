package com.shenchao.bos.web.action;

import com.shenchao.bos.domain.Region;
import com.shenchao.bos.service.IRegionService;
import com.shenchao.bos.utils.PinYin4jUtils;
import com.shenchao.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 区域管理
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	//接收上传的文件
	private File myFile;
	//用于查询的参数
	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	/**
	 * 批量导入
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws Exception{
		String flag = "1";
		//使用ＰＯＩ解析Ｅｘｃｅｌ文件
		try{
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(myFile));
			//获得第一个sheet页
			HSSFSheet sheet = workbook.getSheetAt(0);
			List<Region> list = new ArrayList<Region>();
			for (Row row : sheet) {
				int rowNum = row.getRowNum();
				if(rowNum == 0){
					//第一行，标题行，忽略
					continue;
				}
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				Region region = new Region(id, province, city, district, postcode, null, null, null);
				
				city  = city.substring(0, city.length() - 1);
				String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
				String citycode = StringUtils.join(stringToPinyin, "");
				
				//简码---->>HBSJZCA
				province  = province.substring(0, province.length() - 1);
				district  = district.substring(0, district.length() - 1);
				String info = province + city + district;//河北石家庄长安
				String[] headByString = PinYin4jUtils.getHeadByString(info);
				String shortcode = StringUtils.join(headByString, "");
				
				region.setCitycode(citycode);
				region.setShortcode(shortcode);
				list.add(region);
			}
			regionService.saveBatch(list);
		}catch (Exception e) {
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}

	/**
	 * 分页查询地区
	 * @return
	 * @throws Exception
     */
	public String pageQuery() throws Exception {
		regionService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria","subareas"});
		return NONE;
	}

	public String listAjax() throws IOException {
		String[] excludes = {"subareas"};
		if (StringUtils.isNotBlank(q)) {
			this.writeList2Json(regionService.findByQ(q),excludes);
			return NONE;
		}
		List<Region> all = regionService.findAll();
		this.writeList2Json(all,excludes);
		return NONE;
	}
}
