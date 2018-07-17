package com.iflytek.speech.webspeech.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Json结果解析类
 */
public class JsonParser {

	public static void main(String[] args) {
		String  jsonStr = "[{\"sub\":\"iat\",\"text\":\"武汉的天气怎么样\"},{\"sub\":\"iat\",\"text\":\"\"},{\"sub\":\"nlp\",\"intent\":{}},{\"sub\":\"nlp\",\"intent\":{\"rc\":0,\"semantic\":[{\"slots\":[{\"name\":\"datetime\",\"value\":\"今天\",\"normValue\":\"{\\\"datetime\\\":\\\"2018-07-17\\\",\\\"suggestDatetime\\\":\\\"2018-07-17\\\"}\"},{\"name\":\"location.city\",\"value\":\"武汉市\",\"normValue\":\"武汉市\"},{\"name\":\"location.cityAddr\",\"value\":\"武汉\",\"normValue\":\"武汉\"},{\"name\":\"location.type\",\"value\":\"LOC_BASIC\",\"normValue\":\"LOC_BASIC\"},{\"name\":\"queryType\",\"value\":\"内容\"},{\"name\":\"subfocus\",\"value\":\"天气状态\"}],\"intent\":\"QUERY\"}],\"dialog_stat\":\"DataValid\",\"answer\":{\"text\":\"武汉今天多云，28℃ ~ 37℃，东南风微风\"},\"data\":{\"result\":[{\"date\":\"2018-07-17\",\"airQuality\":\"良\",\"weatherType\":1,\"img\":\"http://aiui-res.ufile.ucloud.com.cn/weather/01.png\",\"temp\":33,\"week\":\"Tuesday\",\"airData\":59,\"city\":\"武汉\",\"windLevel\":0,\"pm25\":\"33\",\"weather\":\"多云\",\"humidity\":\"62%\",\"tempRange\":\"28℃ ~ 37℃\",\"exp\":{\"ct\":{\"expName\":\"穿衣指数\",\"level\":\"炎热\",\"prompt\":\"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。\"},\"uv\":{\"expName\":\"紫外线强度指数\",\"level\":\"中等\",\"prompt\":\"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。\"},\"gj\":{\"expName\":\"逛街指数\",\"level\":\"不适宜\",\"prompt\":\"天气酷热，不适宜逛街，若坚持出门，请您注意选择遮阳地点，并注意防暑降温。\"},\"dy\":{\"expName\":\"钓鱼指数\",\"level\":\"不宜\",\"prompt\":\"天气太热，不适合垂钓。\"},\"gm\":{\"expName\":\"感冒指数\",\"level\":\"少发\",\"prompt\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\"},\"cl\":{\"expName\":\"晨练指数\",\"level\":\"较适宜\",\"prompt\":\"早晨气象条件较适宜晨练，注意选择通风凉爽的地点。适量饮水以及时补充体内水分。\"},\"xc\":{\"expName\":\"洗车指数\",\"level\":\"较适宜\",\"prompt\":\"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"},\"yd\":{\"expName\":\"运动指数\",\"level\":\"适宜\",\"prompt\":\"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。\"},\"co\":{\"expName\":\"舒适度指数\",\"level\":\"很不舒适\",\"prompt\":\"白天天气晴好，但烈日炎炎您会感到很热，很不舒适。\"},\"fs\":{\"expName\":\"防晒指数\",\"level\":\"中等\",\"prompt\":\"属中等强度紫外辐射天气，外出时应注意防护，建议涂擦SPF指数高于15，PA+的防晒护肤品。\"},\"tr\":{\"expName\":\"旅游指数\",\"level\":\"较不宜\",\"prompt\":\"天气较好，微风，但温度高，天气很热，请注意防暑降温，并注意防晒，若外出可选择水上娱乐等清凉项目。\"}},\"dateLong\":1531756800,\"lastUpdateTime\":\"2018-07-17 08:00\",\"wind\":\"东南风微风\"},{\"date\":\"2018-07-18\",\"weatherType\":1,\"img\":\"http://aiui-res.ufile.ucloud.com.cn/weather/01.png\",\"week\":\"Wednesday\",\"city\":\"武汉\",\"windLevel\":1,\"weather\":\"多云转晴\",\"tempRange\":\"28℃ ~ 37℃\",\"dateLong\":1531843200,\"lastUpdateTime\":\"2018-07-17 08:00\",\"wind\":\"东南风3-4级\"},{\"date\":\"2018-07-19\",\"weatherType\":0,\"img\":\"http://aiui-res.ufile.ucloud.com.cn/weather/00.png\",\"week\":\"Thursday\",\"city\":\"武汉\",\"windLevel\":0,\"weather\":\"晴\",\"tempRange\":\"29℃ ~ 37℃\",\"dateLong\":1531929600,\"lastUpdateTime\":\"2018-07-17 08:00\",\"wind\":\"东南风微风\"},{\"date\":\"2018-07-20\",\"weatherType\":0,\"img\":\"http://aiui-res.ufile.ucloud.com.cn/weather/00.png\",\"week\":\"Friday\",\"city\":\"武汉\",\"windLevel\":0,\"weather\":\"晴\",\"tempRange\":\"28℃ ~ 38℃\",\"dateLong\":1532016000,\"lastUpdateTime\":\"2018-07-17 08:00\",\"wind\":\"东南风微风\"},{\"date\":\"2018-07-21\",\"weatherType\":0,\"img\":\"http://aiui-res.ufile.ucloud.com.cn/weather/00.png\",\"week\":\"Saturday\",\"city\":\"武汉\",\"windLevel\":0,\"weather\":\"晴\",\"tempRange\":\"28℃ ~ 37℃\",\"dateLong\":1532102400,\"lastUpdateTime\":\"2018-07-17 08:00\",\"wind\":\"东南风微风\"},{\"date\":\"2018-07-22\",\"weatherType\":1,\"img\":\"http://aiui-res.ufile.ucloud.com.cn/weather/01.png\",\"week\":\"Sunday\",\"city\":\"武汉\",\"windLevel\":0,\"weather\":\"多云转晴\",\"tempRange\":\"28℃ ~ 36℃\",\"dateLong\":1532188800,\"lastUpdateTime\":\"2018-07-17 08:00\",\"wind\":\"东风微风\"},{\"date\":\"2018-07-23\",\"weatherType\":1,\"img\":\"http://aiui-res.ufile.ucloud.com.cn/weather/01.png\",\"week\":\"Monday\",\"city\":\"武汉\",\"windLevel\":0,\"weather\":\"多云转阴\",\"tempRange\":\"24℃ ~ 35℃\",\"dateLong\":1532275200,\"lastUpdateTime\":\"2018-07-17 08:00\",\"wind\":\"东风微风\"}]},\"service\":\"weather\",\"state\":{\"fg::weather::default::default\":{\"state\":\"default\"}},\"text\":\"武汉的天气怎么样\",\"save_history\":true,\"uuid\":\"atn00eb01ef@dx00070ea8aa6aa10e01\",\"sid\":\"ara002ae462@dx1d350ea8aa6a000100\"}}]";
		System.out.println(parseAIUIResult(jsonStr));
	}

	public static String parseAIUIResult(String jsonStr) {
		StringBuffer ret = new StringBuffer();
		try {
			JSONArray jsonArray = JSONObject.parseArray(jsonStr);
			for(Object item : jsonArray) {
				JSONObject resultItem = (JSONObject)item;
				if(resultItem.get("sub").equals("nlp")) {
					Object intent = resultItem.get("intent");
					if(intent != null) {
						Object answer = ((JSONObject)intent).get("answer");
						if(answer != null) {
							ret.append(((JSONObject)answer).get("text"));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ret.toString();
	}
	

}
