<?php

declare(strict_types=1);

namespace App\Http\Controllers\Api\v1;

use App\Http\Requests\LoginRequest;
use App\Http\Resources\UserResource;
use Illuminate\Http\JsonResponse;
use Illuminate\Support\Facades\Auth;
use OpenApi\Attributes as OA;

class LoginController extends Controller
{
    #[OA\Post(
        path: '/api/v1/login',
        summary: 'Вход в админку по логину и паролю',
        requestBody: new OA\RequestBody(
            required: true,
            content: new OA\JsonContent(
                ref: '#/components/schemas/LoginRequest',
            ),
        ),
        tags: ['v1', 'login'],
        responses: [
            new OA\Response(
                response: 200,
                description: 'OK',
                content: new OA\JsonContent(
                    ref: '#/components/schemas/UserResource',
                    type: 'object',
                ),
            ),
            new OA\Response(
                response: 400,
                description: 'В случае неверных входных данных',
            ),
            new OA\Response(
                response: 401,
                description: 'В случае, если введён неверный логин или пароль',
            ),
        ],

    )]
    public function login(LoginRequest $request): JsonResponse
    {
        $credentials = $request->validated();

        if (! Auth::attempt($credentials)) {
            return response()->json(status: 401);
        }

        return (new UserResource(Auth::user()))->response();
    }
}
