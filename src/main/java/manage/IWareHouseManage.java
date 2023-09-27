
package manage;


import entities.WareHouse;

public interface IWareHouseManage {
    void createImportReceipt(WareHouse wareHouse);
    void createExportReceipt(WareHouse wareHouse);
}
