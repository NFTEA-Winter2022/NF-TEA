let NFTEAContract = artifacts.require("../contracts/NFTEA");
module.exports = function (deployer) {
  deployer.deploy(NFTEAContract);
};
