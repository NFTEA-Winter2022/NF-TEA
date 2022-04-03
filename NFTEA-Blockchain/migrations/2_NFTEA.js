let NFTEAContract = artifacts.require("../contracts/NFTEA");
module.exports = function (deployer, network, accounts) {
  const DAN_HOSI = accounts[9];
  deployer.deploy(NFTEAContract, DAN_HOSI);
};
