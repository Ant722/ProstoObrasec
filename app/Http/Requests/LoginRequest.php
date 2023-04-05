<?php

declare(strict_types=1);

namespace App\Http\Requests;

use Illuminate\Contracts\Validation\Rule;
use Illuminate\Foundation\Http\FormRequest;
use OpenApi\Attributes as OA;

#[OA\Schema(
    description: 'Логин и пароль для логина',
    properties: [
        new OA\Property(
            property: 'login',
            description: 'Логин',
            type: 'string',
        ),
        new OA\Property(
            property: 'password',
            description: 'Пароль',
            type: 'string',
        ),
    ],
)]
class LoginRequest extends FormRequest
{
    public function authorize(): bool
    {
        return true;
    }

    /**
     * @return array<string, Rule|array|string>
     */
    public function rules(): array
    {
        return [
            'login' => ['required'],
            'password' => ['required'],
        ];
    }
}
