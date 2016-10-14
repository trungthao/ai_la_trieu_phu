package com.uet.trungthao.ailatrieuphu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by JiH on 14/10/2016.
 */

public class RelativeActivity extends Activity {

    private ImageView ivBanThan, ivBanDeu, ivBoBich, ivHangXom, ivNguoiNha, ivDongNghiep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);

        addController();
        addEvents();
    }

    private void addEvents() {
        chooseRelative(ivBanThan);
        chooseRelative(ivBoBich);
        chooseRelative(ivBanDeu);
        chooseRelative(ivNguoiNha);
        chooseRelative(ivHangXom);
        chooseRelative(ivDongNghiep);
    }


    private void chooseRelative(ImageView ivBanDeu) {
        ivBanDeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: xu ly chon nguoi than, tam thoi de Toat dap an
                finish();
            }
        });
    }

    private void addController() {
        ivBanDeu = (ImageView) findViewById(R.id.iv_ban_deu);
        ivBanThan= (ImageView) findViewById(R.id.iv_ban_than);
        ivBoBich = (ImageView) findViewById(R.id.iv_bo_bich);
        ivHangXom = (ImageView) findViewById(R.id.iv_hang_xom);
        ivNguoiNha = (ImageView) findViewById(R.id.iv_nguoi_nha);
        ivDongNghiep = (ImageView) findViewById(R.id.iv_dong_nghiep);
    }
}
