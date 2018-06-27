
package com.itislevel.lyl.mvp.ui.email.utils;

import android.support.v4.util.ArrayMap;

import com.itislevel.lyl.R;


/**
 * @author : zejian
 * @time : 2016年1月5日 上午11:32:33
 * @email : shinezejian@163.com
 * @description :表情加载类,可自己添加多种表情，分别建立不同的map存放和不同的标志符即可
 */
public class EmotionUtils {

	/**
	 * 表情类型标志符
	 */
	public static final int EMOTION_CLASSIC_TYPE=0x0001;//经典表情

	/**
	 * key-表情文字;
	 * value-表情图片资源
	 */
	public static ArrayMap<String, Integer> EMPTY_MAP;
	public static ArrayMap<String, Integer> EMOTION_CLASSIC_MAP;


	 static {
		EMPTY_MAP = new ArrayMap<>();
		EMOTION_CLASSIC_MAP = new ArrayMap<>();
		EMOTION_CLASSIC_MAP.put("[可爱]", R.drawable.emoji_1);
		EMOTION_CLASSIC_MAP.put("[笑脸]", R.drawable.emoji_2);
		EMOTION_CLASSIC_MAP.put("[囧]", R.drawable.emoji_3);
		EMOTION_CLASSIC_MAP.put("[生气]", R.drawable.emoji_4);
		EMOTION_CLASSIC_MAP.put("[鬼脸]", R.drawable.emoji_5);
		EMOTION_CLASSIC_MAP.put("[花心]", R.drawable.emoji_6);
		EMOTION_CLASSIC_MAP.put("[害怕]", R.drawable.emoji_7);
		EMOTION_CLASSIC_MAP.put("[我汗]", R.drawable.emoji_8);
		EMOTION_CLASSIC_MAP.put("[尴尬]", R.drawable.emoji_9);
		EMOTION_CLASSIC_MAP.put("[哼哼]", R.drawable.emoji_10);

		EMOTION_CLASSIC_MAP.put("[忧郁]", R.drawable.emoji_11);
		EMOTION_CLASSIC_MAP.put("[呲牙]", R.drawable.emoji_12);
		EMOTION_CLASSIC_MAP.put("[媚眼]", R.drawable.emoji_13);
		EMOTION_CLASSIC_MAP.put("[累]", R.drawable.emoji_14);
		EMOTION_CLASSIC_MAP.put("[苦逼]", R.drawable.emoji_15);
		EMOTION_CLASSIC_MAP.put("[瞌睡]", R.drawable.emoji_16);
		EMOTION_CLASSIC_MAP.put("[哎呀]", R.drawable.emoji_17);
		EMOTION_CLASSIC_MAP.put("[刺瞎]", R.drawable.emoji_18);
		EMOTION_CLASSIC_MAP.put("[哭]", R.drawable.emoji_19);
		EMOTION_CLASSIC_MAP.put("[激动]", R.drawable.emoji_20);

		EMOTION_CLASSIC_MAP.put("[难过]", R.drawable.emoji_21);
		EMOTION_CLASSIC_MAP.put("[害羞]", R.drawable.emoji_22);
		EMOTION_CLASSIC_MAP.put("[高兴]", R.drawable.emoji_23);
		EMOTION_CLASSIC_MAP.put("[愤怒]", R.drawable.emoji_24);
		EMOTION_CLASSIC_MAP.put("[亲]", R.drawable.emoji_25);
		EMOTION_CLASSIC_MAP.put("[飞吻]", R.drawable.emoji_26);
		EMOTION_CLASSIC_MAP.put("[得意]", R.drawable.emoji_27);
		EMOTION_CLASSIC_MAP.put("[惊恐]", R.drawable.emoji_28);
		EMOTION_CLASSIC_MAP.put("[口罩]", R.drawable.emoji_29);
		EMOTION_CLASSIC_MAP.put("[惊讶]", R.drawable.emoji_30);


		EMOTION_CLASSIC_MAP.put("[委屈]", R.drawable.emoji_31);
		EMOTION_CLASSIC_MAP.put("[生病]", R.drawable.emoji_32);
		EMOTION_CLASSIC_MAP.put("[红心]", R.drawable.emoji_33);
		EMOTION_CLASSIC_MAP.put("[心碎]", R.drawable.emoji_34);
		EMOTION_CLASSIC_MAP.put("[玫瑰]", R.drawable.emoji_35);
		EMOTION_CLASSIC_MAP.put("[花]", R.drawable.emoji_36);
		EMOTION_CLASSIC_MAP.put("[外星人]", R.drawable.emoji_37);
		EMOTION_CLASSIC_MAP.put("[金牛座]", R.drawable.emoji_38);
		EMOTION_CLASSIC_MAP.put("[双子座]", R.drawable.emoji_39);
		EMOTION_CLASSIC_MAP.put("[巨蟹座]", R.drawable.emoji_40);

		EMOTION_CLASSIC_MAP.put("[狮子座]", R.drawable.emoji_41);
		EMOTION_CLASSIC_MAP.put("[处女座]", R.drawable.emoji_42);
		EMOTION_CLASSIC_MAP.put("[天平座]", R.drawable.emoji_43);
		EMOTION_CLASSIC_MAP.put("[天蝎座]", R.drawable.emoji_44);
		EMOTION_CLASSIC_MAP.put("[射手座]", R.drawable.emoji_45);
		EMOTION_CLASSIC_MAP.put("[摩羯座]", R.drawable.emoji_46);
		EMOTION_CLASSIC_MAP.put("[水瓶座]", R.drawable.emoji_47);


		EMOTION_CLASSIC_MAP.put("[白羊座]", R.drawable.emoji_48);
		EMOTION_CLASSIC_MAP.put("[双鱼座]", R.drawable.emoji_49);
		EMOTION_CLASSIC_MAP.put("[星座]", R.drawable.emoji_50);
		EMOTION_CLASSIC_MAP.put("[男孩]", R.drawable.emoji_51);
		EMOTION_CLASSIC_MAP.put("[女孩]", R.drawable.emoji_52);
		EMOTION_CLASSIC_MAP.put("[嘴唇]", R.drawable.emoji_53);
		EMOTION_CLASSIC_MAP.put("[爸爸]", R.drawable.emoji_54);
		EMOTION_CLASSIC_MAP.put("[妈妈]", R.drawable.emoji_55);
		EMOTION_CLASSIC_MAP.put("[衣服]", R.drawable.emoji_56);
		EMOTION_CLASSIC_MAP.put("[皮鞋]", R.drawable.emoji_57);

		EMOTION_CLASSIC_MAP.put("[照相]", R.drawable.emoji_58);
		EMOTION_CLASSIC_MAP.put("[电话]", R.drawable.emoji_59);
		EMOTION_CLASSIC_MAP.put("[石头]", R.drawable.emoji_60);
		EMOTION_CLASSIC_MAP.put("[胜利]", R.drawable.emoji_61);
		EMOTION_CLASSIC_MAP.put("[禁止]", R.drawable.emoji_62);
		EMOTION_CLASSIC_MAP.put("[滑雪]", R.drawable.emoji_63);
		EMOTION_CLASSIC_MAP.put("[高尔夫]", R.drawable.emoji_64);
		EMOTION_CLASSIC_MAP.put("[网球]", R.drawable.emoji_65);
		EMOTION_CLASSIC_MAP.put("[棒球]", R.drawable.emoji_66);
		EMOTION_CLASSIC_MAP.put("[冲浪]", R.drawable.emoji_67);
	}

	/**
	 * 根据名称获取当前表情图标R值
	 * @param EmotionType 表情类型标志符
	 * @param imgName 名称
	 * @return
	 */
	public static int getImgByName(int EmotionType,String imgName) {
		Integer integer=null;
		switch (EmotionType){
			case EMOTION_CLASSIC_TYPE:
				integer = EMOTION_CLASSIC_MAP.get(imgName);
				break;
			default:
				LogUtils.e("the emojiMap is null!! Handle Yourself ");
				break;
		}
		return integer == null ? -1 : integer;
	}

	/**
	 * 根据类型获取表情数据
	 * @param EmotionType
	 * @return
	 */
	public static ArrayMap<String, Integer> getEmojiMap(int EmotionType){
		ArrayMap EmojiMap=null;
		switch (EmotionType){
			case EMOTION_CLASSIC_TYPE:
				EmojiMap=EMOTION_CLASSIC_MAP;
				break;
			default:
				EmojiMap=EMPTY_MAP;
				break;
		}
		return EmojiMap;
	}
}
