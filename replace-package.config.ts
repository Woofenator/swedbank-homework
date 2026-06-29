import path from 'node:path';
import { Configuration } from 'webpack';

export default {
    resolve: {
        alias: {
            html2canvas: path.resolve(
                __dirname,
                'node_modules/html2canvas-pro',
            ),
        },
    },
} as Configuration;
