/*
 * Copyright (c) 2007-2009 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Cascading is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cascading is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cascading.  If not, see <http://www.gnu.org/licenses/>.
 */

package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.builder.AnnotationClassParam.AnnotationClassParamHelper;
import unquietcode.tools.flapi.builder.AnnotationParam.AnnotationParamHelper;
import unquietcode.tools.flapi.builder.Annotations.AnnotationsHelper;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
public class AnnotationsHelperImpl implements AnnotationsHelper {
    private final MethodOutline method;
    private final String annotation;

    public AnnotationsHelperImpl(MethodOutline method, String annotation) {
        this.method = method;
        this.annotation = annotation;
    }

    @Override
    public void finish() {

    }

    @Override
    public void withClassParam(final String param, AtomicReference<AnnotationClassParamHelper> _helper1) {
        _helper1.set(new AnnotationClassParamHelper() {
            @Override
            public void havingValue(String value) {
                method.addAnnotationClassParam(annotation, param, value);
            }

            @Override
            public void havingValue(Class value) {
                method.addAnnotationClassParam(annotation, param, value);
            }
        });
    }

    @Override
    public void withParam(final String param, AtomicReference<AnnotationParamHelper> _helper1) {
        _helper1.set(new AnnotationParamHelper() {
            @Override
            public void havingValue(Enum value) {
                method.addAnnotationParam(annotation, param, value);
            }

            @Override
            public void havingValue(String value) {
                method.addAnnotationParam(annotation, param, value);
            }

            @Override
            public void havingValue(boolean value) {
                method.addAnnotationParam(annotation, param, value);
            }

            @Override
            public void havingValue(byte value) {
                method.addAnnotationParam(annotation, param, value);
            }

            @Override
            public void havingValue(double value) {
                method.addAnnotationParam(annotation, param, value);
            }

            @Override
            public void havingValue(float value) {
                method.addAnnotationParam(annotation, param, value);
            }

            @Override
            public void havingValue(int value) {
                method.addAnnotationParam(annotation, param, value);
            }

            @Override
            public void havingValue(long value) {
                method.addAnnotationParam(annotation, param, value);
            }

            @Override
            public void havingValue(short value) {
                method.addAnnotationParam(annotation, param, value);
            }
        });
    }
}
