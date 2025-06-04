@SneakyThrows
@Test(groups = {POSITIVE},enabled = false)
public void testReverseSyncDelete() {

    ApiAutomationLogger.log("Creation of Fulfillment using Stager");

    Map<String, Object> dataMap = CommonUtils.createRelationshipfromStager();
    AppResponse getResponse = CommonUtils.getRelationshipByParty(dataMap, OdataApis.RELATIONSHIP_BY_SOURCE, "Buyer", true);
    AssertionUtil.assertEquals("Status Check for Relationship By Source", HttpStatus.SC_OK, getResponse.getHttpStatusCode().intValue());

    String relationshipID = CommonUtils.getFieldValue(getResponse, "id");

    dataMap.put("BuyerAnid", CommonUtils.getFieldValue(getResponse, "ANID"));
    dataMap.put("SupplierAnid", CommonUtils.getFieldValue(getResponse, "targetANID"));


    AppResponse appResponse = CommonUtils.deleteRelationship(dataMap, relationshipID, "Buyer", true);
    AssertionUtil.assertEquals("Status Check for Delete Relationship", HttpStatus.SC_NO_CONTENT, appResponse.getHttpStatusCode().intValue());

    ApiAutomationLogger.log("Check if the relationship Status in AN(Gen1) got updated to disabled after delete from Nextgen");

    AssertionUtil.assertEquals("Status Check for Delete Relationship","Disabled", CommonUtils.checkRelationshipStatusAN(dataMap));

}


  String stagerUrlToHit = "https://svcgcpan" + EnvConfigData.getInstance().getGenOne() + ".lab-us.gcpint.ariba.com" + CREATE_RELATIONSHIP_ACCOUNT_STAGER;
//
//        Long timestamp = new Date().getTime();
//
//
       JsonNode relationReq = CommonUtil.getObject(new File(CREATE_TEST_ACC_WITH_RELATIONSHIP_REQUEST_JSON_PATH), JsonNode.class);

ApiAutomationLogger.log("Request Details of Create Relationship : " + reqJson);
        AppResponse createResp = ApiExecuteUtil.post(stagerUrlToHit, reqJson);
//
       AssertionUtil.assertEquals("Status Check for A/c Created with Relationship", HttpStatus.SC_OK, createResp.getHttpStatusCode().intValue());
//     



