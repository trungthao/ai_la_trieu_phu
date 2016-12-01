package com.uet.trungthao.ailatrieuphu.activity_helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.uet.trungthao.ailatrieuphu.R;

/**
 * Created by JiH on 14/10/2016.
 */

public class RelativeActivity extends Activity {

    private ImageView ivBanThan, ivBanDeu, ivBoBich, ivHangXom, ivNguoiNha, ivDongNghiep;
    private int caseRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);

        Intent mIntent = getIntent();
        caseRelative = mIntent.getIntExtra("caseRelative", 0);
        addController();
        addEvents();
    }

    private void addEvents() {
        chooseRelative(ivBanThan, "Bạn thân");
        chooseRelative(ivBoBich, "Bồ bịch");
        chooseRelative(ivBanDeu, "Bạn đểu");
        chooseRelative(ivNguoiNha, "Người nhà");
        chooseRelative(ivHangXom, "Hàng xóm");
        chooseRelative(ivDongNghiep, "Đồng nghiệp");
    }


    private void chooseRelative(ImageView ivRelative, final String nameRelative) {
        ivRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: xu ly chon nguoi than, tam thoi de Toat dap an
                Intent mIntent = new Intent(RelativeActivity.this, ShowCaseRelativeActivity.class);
                mIntent.putExtra("nameRelative", nameRelative);
                mIntent.putExtra("caseRelative", caseRelative);
                startActivity(mIntent);
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
