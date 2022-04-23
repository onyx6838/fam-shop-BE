package com.fam.entity.enumerate;

public enum TrangThaiDonDat {
    DON_DAT, HOA_DON, VAN_DON, HUY_DON
    // DON_DAT: đơn do người mua đặt với cửa hàng
    // VAN_DON (vận đơn) : sau khi xác nhận là mua hàng thì chuyển thành vận đơn
    // HOA_DON: khi gửi giấy thanh toán cuối cùng cho khách hàng
    // HUY_DON: khi khách hủy đơn lúc gọi điện xác nhận đơn
}
