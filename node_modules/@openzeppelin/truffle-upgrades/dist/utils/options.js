"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.withDefaults = void 0;
const truffle_1 = require("./truffle");
const upgrades_core_1 = require("@openzeppelin/upgrades-core");
function withDefaults(opts = {}) {
    var _a, _b;
    return {
        deployer: (_a = opts.deployer) !== null && _a !== void 0 ? _a : defaultDeployer,
        constructorArgs: (_b = opts.constructorArgs) !== null && _b !== void 0 ? _b : [],
        ...(0, upgrades_core_1.withValidationDefaults)(opts),
    };
}
exports.withDefaults = withDefaults;
const defaultDeployer = {
    get provider() {
        return (0, truffle_1.getTruffleConfig)().provider;
    },
    async deploy(Contract, ...args) {
        return Contract.new(...args);
    },
};
//# sourceMappingURL=options.js.map