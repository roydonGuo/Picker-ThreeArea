package com.roydon.pickerapp1.slice;

import com.roydon.pickerapp1.ResourceTable;
import com.roydon.pickerapp1.bean.City;
import com.roydon.pickerapp1.bean.Province;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Picker;

import java.util.ArrayList;

public class MainAbilitySlice extends AbilitySlice implements Picker.ValueChangedListener {

    Picker province;
    Picker city;
    Picker district;
    ArrayList<Province> provinceList;
    ArrayList<City> cityList;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

//        Picker picker = (Picker) findComponentById(ResourceTable.Id_picker);
//
//        ArrayList<String> list = new ArrayList<>();
//        list.add("星期一");
//        list.add("星期二");
//        list.add("星期三");
//        list.add("星期四");
//        list.add("星期五");
//        list.add("星期六");
//        list.add("星期日");
//
////        picker.setFormatter(i-> list.get(i));
//        picker.setFormatter(list::get);//方法引用
        province = (Picker) findComponentById(ResourceTable.Id_province);
        city = (Picker) findComponentById(ResourceTable.Id_city);
        district = (Picker) findComponentById(ResourceTable.Id_district);


        provinceList = getData();

        province.setMaxValue(provinceList.size() - 1);
        province.setFormatter(i -> provinceList.get(i).getName());

        city.setMaxValue(provinceList.get(0).getCitys().size() - 1);
        city.setFormatter(i -> provinceList.get(0).getCitys().get(i).getName());

        district.setMaxValue(provinceList.get(0).getCitys().get(0).getDistricts().size() - 1);
        district.setFormatter(i -> provinceList.get(0).getCitys().get(0).getDistricts().get(i));

        province.setValueChangedListener(this);

        city.setValueChangedListener(this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    // 记录当前选择的是哪个省，需要根据它获取对应的市
    int proIn = 0;

    @Override
    public void onValueChanged(Picker picker, int oldV, int newV) {
        if (picker == province) {
            Province chooseProvince = provinceList.get(newV);
            proIn = newV;
            // 省变了，是要跟着变
            city.setMaxValue(chooseProvince.getCitys().size() - 1);
            city.setFormatter(i -> chooseProvince.getCitys().get(i).getName());
            city.setValue(0);//setValue(0)：默认展示第一个
            // 省变了，区也要跟着变
            district.setMaxValue(chooseProvince.getCitys().get(0).getDistricts().size() - 1);
            district.setFormatter(chooseProvince.getCitys().get(0).getDistricts()::get);
            district.setValue(0);
        }
        if (picker == city) {
            City chooseCity = provinceList.get(proIn).getCitys().get(newV);
            district.setMaxValue(chooseCity.getDistricts().size() - 1);
            district.setFormatter(chooseCity.getDistricts()::get);
            district.setValue(0);
        }

    }

    private ArrayList<Province> getData() {
        ArrayList<Province> provinces = new ArrayList<>();

        ArrayList<City> henancities = new ArrayList<>();//河南

        ArrayList<String> zhengzhoudistricts = new ArrayList<>();

        zhengzhoudistricts.add("中原区");
        zhengzhoudistricts.add("金水区");
        zhengzhoudistricts.add("二七区");

        henancities.add(new City("郑州", zhengzhoudistricts));
        ArrayList<String> shangqiudistricts = new ArrayList<>();

        shangqiudistricts.add("永城");
        shangqiudistricts.add("夏邑");
        shangqiudistricts.add("民权");

        henancities.add(new City("商丘", shangqiudistricts));

        ArrayList<City> hebeicities = new ArrayList<>();//河北

        ArrayList<String> tangshandistricts = new ArrayList<>();

        tangshandistricts.add("路南区");
        tangshandistricts.add("路北区");
        tangshandistricts.add("路东区");

        hebeicities.add(new City("唐山市", tangshandistricts));
        ArrayList<String> shijiazhuangdistricts = new ArrayList<>();

        shijiazhuangdistricts.add("长安区");
        shijiazhuangdistricts.add("桥西区");
        shijiazhuangdistricts.add("22区");

        hebeicities.add(new City("石家庄市", shijiazhuangdistricts));


        provinces.add(new Province("河南", henancities));
        provinces.add(new Province("河北", hebeicities));

        return provinces;

    }

}
